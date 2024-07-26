package com.medhead.api.repository;

import com.medhead.api.model.Hospital;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospitalRepository extends CrudRepository<Hospital, Integer>, HospitalRepositoryCustom {

    //TODO voir si je laisse ça ici ou si je le met dans le custom
    @Query(nativeQuery = true, value="SELECT * FROM hospital WHERE spec LIKE ':spe'")
    List<Hospital> findBySpecialities(@Param("spec") String spec);
}
