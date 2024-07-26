package com.medhead.api.controller;

import com.medhead.api.exceptions.HospitalNotFoundException;
import com.medhead.api.exceptions.MissingParametersException;
import com.medhead.api.model.Hospital;
import com.medhead.api.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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

    @RequestMapping("/speciality")
    public HttpEntity<String> noSpecialityEntered() throws MissingParametersException {
        throw new MissingParametersException("Warning: missing the speciality parameter");
    }

    @GetMapping("/speciality/{speciality}")
    public Iterable<Hospital> getAllBySpeciality(@PathVariable("speciality") final String speciality) throws HospitalNotFoundException {
        ArrayList<Hospital> hospitals = (ArrayList<Hospital>) hospitalService.findBySpecialities(speciality);
        if (!hospitals.isEmpty()) {
            return hospitals;
        } else {
            throw new HospitalNotFoundException("No hospitals found in the database with speciality " + speciality);
        }
    }

    @GetMapping("/available")
    public Iterable<Hospital> getAllavailableBedsHospitals() throws HospitalNotFoundException {
        Iterable<Hospital> hospitals = hospitalService.findByAvailableBeds();
        if (hospitals != null) {
            return hospitals; //TODO modifier pour utiliser du json?
        } else {
            throw new HospitalNotFoundException("No hospitals with available beds found in the database");
        }
    }
}
