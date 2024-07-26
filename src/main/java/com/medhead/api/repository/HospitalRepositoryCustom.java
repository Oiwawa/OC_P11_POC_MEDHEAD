package com.medhead.api.repository;

import com.medhead.api.model.Hospital;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HospitalRepositoryCustom {
    List<Hospital> findByAvailableBeds();

//    TODO utiliser ce format @Query ou dans le custom impl ?
//    List<Hospital> findBySpecialities(String spec);
    @Query(nativeQuery = true, value="SELECT * FROM hospital WHERE spec LIKE ':spec'")
    List<Hospital> findBySpecialities(@Param("spec") String spec);

    List<Hospital> findByAvailableBedsAndBySpecialities(String spec);
}
