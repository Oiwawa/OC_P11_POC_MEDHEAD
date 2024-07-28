package com.medhead.api.service;

import com.medhead.api.model.Hospital;
import com.medhead.api.repository.HospitalRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Service class for managing hospitals.
 */
@Data
@Service
public class HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    /**
     * Retrieves a hospital by its ID.
     *
     * @param id the ID of the hospital
     * @return an optional containing the hospital if found
     */
    public Optional<Hospital> getHospital(final Integer id) {
        return hospitalRepository.findById(id);
    }

    /**
     * Retrieves all hospitals.
     *
     * @return an iterable of all hospitals
     */
    public Iterable<Hospital> getAllHospital() {
        return hospitalRepository.findAll();
    }

    /**
     * Retrieves all specialities offered by hospitals.
     *
     * @return a set of all specialities
     */
    public Set<String> getAllSpecialities() {
        List<String> specialitiesList = hospitalRepository.findAllSpecialities();
        return new HashSet<>(specialitiesList);
    }

    /**
     * Retrieves all hospitals with available beds.
     *
     * @return a list of hospitals with available beds
     */
    public List<Hospital> findByAvailableBeds() {
        return hospitalRepository.findByAvailableBeds();
    }

    /**
     * Retrieves all hospitals that offer a specific speciality.
     *
     * @param speciality the speciality to filter hospitals by
     * @return a list of hospitals that offer the specified speciality
     */
    public List<Hospital> findBySpeciality(String speciality) {
        return hospitalRepository.findBySpeciality(speciality);
    }

    /**
     * Searches for hospitals based on various parameters.
     *
     * @param speciality    the speciality to filter hospitals by (optional)
     * @param availableBeds whether to filter hospitals by availability of beds (optional)
     * @param latInit       the initial latitude for location-based search (optional)
     * @param longInit      the initial longitude for location-based search (optional)
     * @param distance      the distance for location-based search (optional)
     * @return a list of hospitals that match the search criteria
     */
    public List<Hospital> searchHospitals(String speciality, Boolean availableBeds, Float latInit, Float longInit, Integer distance) {
        List<Hospital> hospitals;

        if (availableBeds != null && availableBeds) {
            hospitals = (speciality != null) ?
                    hospitalRepository.findByAvailableBedsAndBySpecialities(speciality) :
                    hospitalRepository.findByAvailableBeds();
        } else {
            hospitals = (speciality != null) ?
                    hospitalRepository.findBySpeciality(speciality) :
                    (List<Hospital>) hospitalRepository.findAll();
        }

        if (latInit != null && longInit != null && distance != null) {
            hospitals = filterHospitalsInRange(hospitals, latInit, longInit, distance);
        }

        return hospitals;
    }

    /**
     * Filters hospitals within a certain distance from a given location.
     *
     * @param hospitals the list of hospitals to filter
     * @param latInit   the initial latitude
     * @param longInit  the initial longitude
     * @param distance  the maximum distance
     * @return a list of hospitals within the given distance
     */
    private List<Hospital> filterHospitalsInRange(List<Hospital> hospitals, float latInit, float longInit, int distance) {
        return hospitals.stream()
                .filter(h -> calculateDistance(latInit, longInit, h.getLatitude(), h.getLongitude()) <= distance)
                .collect(Collectors.toList());
    }

    /**
     * Calculates the distance between two geographical points.
     *
     * @param lat1 the latitude of the first point
     * @param lon1 the longitude of the first point
     * @param lat2 the latitude of the second point
     * @param lon2 the longitude of the second point
     * @return the distance in kilometers
     */
    private double calculateDistance(float lat1, float lon1, float lat2, float lon2) {
        double earthRadius = 6371; // km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return earthRadius * c;
    }
}
