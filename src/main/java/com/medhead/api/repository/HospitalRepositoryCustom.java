package com.medhead.api.repository;

import com.medhead.api.model.Hospital;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HospitalRepositoryCustom {
    List<Hospital> findByAvailableBeds();

//    TODO utiliser ce format @Query ou dans le custom impl ?
//    List<Hospital> findBySpecialities(String specialty);
    @Query(nativeQuery = true, value="SELECT * FROM hospital WHERE specialty LIKE ':specialty'")
    List<Hospital> findBySpecialities(@Param("specialty") String specialty);

    List<Hospital> findByAvailableBedsAndBySpecialities(String specialty);
}
