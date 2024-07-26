package com.medhead.api.controller;

import com.medhead.api.exceptions.HospitalNotFoundException;
import com.medhead.api.model.Hospital;
import com.medhead.api.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

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
}
