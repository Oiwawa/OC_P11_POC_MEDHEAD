package com.medhead.api.service;

import com.medhead.api.model.Hospital;
import com.medhead.api.repository.HospitalRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

//TODO regarder pour test les collections à la place de List
    public List<Hospital> findByAvailableBeds() {
        return hospitalRepository.findByAvailableBeds();
    }

    public List<Hospital> findBySpecialities(String speciality) {
        return hospitalRepository.findBySpecialities(speciality);
    }

}
