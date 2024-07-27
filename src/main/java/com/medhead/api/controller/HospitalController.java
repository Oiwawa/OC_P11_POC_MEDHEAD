package com.medhead.api.controller;

import com.medhead.api.exceptions.HospitalNotFoundException;
import com.medhead.api.model.Hospital;
import com.medhead.api.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/hospital")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    @RequestMapping("/")
    public void handleRequest() {
        throw new RuntimeException("Root exception");
    }

    @GetMapping
    public Iterable<Hospital> getHospitals() throws HospitalNotFoundException {
        Iterable<Hospital> hospitals = hospitalService.getAllHospital();
        if (hospitals != null) {
            return hospitals;
        } else {
            throw new HospitalNotFoundException("No hospitals found in the database");
        }
    }

    @GetMapping("/{id}")
    public Optional<Hospital> getHospital(@PathVariable("id") final Integer id) throws HospitalNotFoundException {
        Optional<Hospital> hospital = hospitalService.getHospital(id);
        if (hospital.isPresent()) {
            return hospital;
        } else {
            throw new HospitalNotFoundException("No hospital found in the database with ID " + id);
        }
    }

    @RequestMapping("/specialities")
    public ResponseEntity<Set<String>> getAllSpecialities() {
        Set<String> specialities = hospitalService.getAllSpecialities();
        return new ResponseEntity<>(specialities, HttpStatus.OK);
    }

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
