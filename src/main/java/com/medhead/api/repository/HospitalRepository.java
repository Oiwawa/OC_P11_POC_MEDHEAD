package com.medhead.api.repository;

import com.medhead.api.model.Hospital;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends CrudRepository<Hospital, Integer>, HospitalRepositoryCustom {

}
