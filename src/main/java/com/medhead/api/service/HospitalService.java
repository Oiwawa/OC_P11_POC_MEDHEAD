package com.medhead.api.service;

import com.medhead.api.model.Hospital;
import com.medhead.api.repository.HospitalRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Data
@Service
public class HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;


    public Optional<Hospital> getHospital(final Integer id) {
        return hospitalRepository.findById(id);
    }

    public Iterable<Hospital> getAllHospital() {
        return hospitalRepository.findAll();
    }

    public Set<String> getAllSpecialities() {
        List<String> specialitiesList = hospitalRepository.findAllSpecialities();
        return new HashSet<>(specialitiesList);
    }

    public List<Hospital> searchHospitals(String speciality, Boolean availableBeds, Float latInit, Float longInit, Integer distance) {
        List<Hospital> hospitals;

        if (availableBeds != null && availableBeds) {
            hospitals = (speciality != null) ?
                    hospitalRepository.findByAvailableBedsAndBySpecialities(speciality) :
                    hospitalRepository.findByAvailableBeds();
        } else {
            hospitals = (speciality != null) ?
                    hospitalRepository.findBySpecialities(speciality) :
                    (List<Hospital>) hospitalRepository.findAll();
        }

        if (latInit != null && longInit != null && distance != null) {
            hospitals = filterHospitalsInRange(hospitals, latInit, longInit, distance);
        }

        return hospitals;
    }

    private List<Hospital> filterHospitalsInRange(List<Hospital> hospitals, float latInit, float longInit, int distance) {
        return hospitals.stream()
                .filter(h -> calculateDistance(latInit, longInit, h.getLatitude(), h.getLongitude()) <= distance)
                .collect(Collectors.toList());
    }

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
