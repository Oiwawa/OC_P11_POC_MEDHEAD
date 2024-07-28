package com.medhead.api.controller;

import com.medhead.api.exceptions.HospitalNotFoundException;
import com.medhead.api.model.Hospital;
import com.medhead.api.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * REST controller for managing hospitals.
 */
@RestController
@RequestMapping("/hospital")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    /**
     * Handles requests to the root URL.
     * Throws a RuntimeException to indicate this endpoint is not implemented.
     */
    @RequestMapping("/")
    public void handleRequest() {
        throw new RuntimeException("Root exception");
    }

    /**
     * Retrieves all hospitals.
     *
     * @return an iterable of all hospitals
     * @throws HospitalNotFoundException if no hospitals are found in the database
     */
    @GetMapping
    public Iterable<Hospital> getHospitals() throws HospitalNotFoundException {
        Iterable<Hospital> hospitals = hospitalService.getAllHospital();
        if (hospitals != null) {
            return hospitals;
        } else {
            throw new HospitalNotFoundException("No hospitals found in the database");
        }
    }

    /**
     * Retrieves a hospital by its ID.
     *
     * @param id the ID of the hospital
     * @return an optional containing the hospital if found
     * @throws HospitalNotFoundException if no hospital is found with the given ID
     */
    @GetMapping("/{id}")
    public Optional<Hospital> getHospital(@PathVariable("id") final Integer id) throws HospitalNotFoundException {
        Optional<Hospital> hospital = hospitalService.getHospital(id);
        if (hospital.isPresent()) {
            return hospital;
        } else {
            throw new HospitalNotFoundException("No hospital found in the database with ID " + id);
        }
    }

    /**
     * Retrieves all specialities offered by hospitals.
     *
     * @return a response entity containing a set of all specialities
     */
    @RequestMapping("/specialities")
    public ResponseEntity<Set<String>> getAllSpecialities() {
        Set<String> specialities = hospitalService.getAllSpecialities();
        return new ResponseEntity<>(specialities, HttpStatus.OK);
    }

    /**
     * Retrieves all hospitals that offer a specific speciality.
     *
     * @param speciality the speciality to filter hospitals by
     * @return an iterable of hospitals that offer the specified speciality
     * @throws HospitalNotFoundException if no hospitals are found with the given speciality
     */
    @GetMapping("/speciality/{speciality}")
    public Iterable<Hospital> getAllBySpeciality(@PathVariable("speciality") final String speciality) throws HospitalNotFoundException {
        ArrayList<Hospital> hospitals = (ArrayList<Hospital>) hospitalService.findBySpeciality(speciality);
        if (!hospitals.isEmpty()) {
            return hospitals;
        } else {
            throw new HospitalNotFoundException("No hospitals found in the database with speciality " + speciality);
        }
    }

    /**
     * Retrieves all hospitals with available beds.
     *
     * @return an iterable of hospitals with available beds
     * @throws HospitalNotFoundException if no hospitals with available beds are found
     */
    @GetMapping("/available")
    public Iterable<Hospital> getAllavailableBedsHospitals() throws HospitalNotFoundException {
        Iterable<Hospital> hospitals = hospitalService.findByAvailableBeds();
        if (hospitals != null) {
            return hospitals;
        } else {
            throw new HospitalNotFoundException("No hospitals with available beds found in the database");
        }
    }

    /**
     * Searches for hospitals based on various parameters.
     *
     * @param speciality    the speciality to filter hospitals by (optional)
     * @param availableBeds whether to filter hospitals by availability of beds (optional)
     * @param latInit       the initial latitude for location-based search (optional)
     * @param longInit      the initial longitude for location-based search (optional)
     * @param distance      the distance for location-based search (optional)
     * @return an iterable of hospitals that match the search criteria
     * @throws HospitalNotFoundException if no hospitals are found with the given parameters
     */
    @GetMapping("/search")
    public Iterable<Hospital> searchHospitals(
            @RequestParam(value = "speciality", required = false) String speciality,
            @RequestParam(value = "availableBeds", required = false) Boolean availableBeds,
            @RequestParam(value = "latInit", required = false) Float latInit,
            @RequestParam(value = "longInit", required = false) Float longInit,
            @RequestParam(value = "distance", required = false) Integer distance) throws HospitalNotFoundException {

        List<Hospital> hospitals = hospitalService.searchHospitals(speciality, availableBeds, latInit, longInit, distance);

        if (!hospitals.isEmpty()) {
            return hospitals;
        } else {
            throw new HospitalNotFoundException("No hospitals found with the given parameters");
        }
    }

}
