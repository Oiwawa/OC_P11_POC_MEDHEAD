package com.medhead.api.repository;

import com.medhead.api.model.Hospital;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HospitalRepositoryCustom {
    List<Hospital> findByAvailableBeds();
    List<Hospital> findBySpecialities(String speciality);
    List<Hospital> findByAvailableBedsAndBySpecialities(String speciality);
}
