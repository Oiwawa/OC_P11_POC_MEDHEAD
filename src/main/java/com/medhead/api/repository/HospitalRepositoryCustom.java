package com.medhead.api.repository;

import com.medhead.api.model.Hospital;

import java.util.List;

public interface HospitalRepositoryCustom {

    List<String> findAllSpecialities();
    List<Hospital> findByAvailableBeds();
    List<Hospital> findBySpeciality(String speciality);
    List<Hospital> findByAvailableBedsAndBySpecialities(String speciality);
}
