package com.medhead.api.repository;

import com.medhead.api.model.Hospital;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class HospitalRepositoryCustomImpl implements HospitalRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Hospital> findByAvailableBeds() {
        TypedQuery<Hospital> query = entityManager.createQuery("SELECT h FROM Hospital h WHERE h.available_beds > 0", Hospital.class);
        return query.getResultList();
    }

    @Override
    public List<Hospital> findBySpeciality(String speciality) {
        TypedQuery<Hospital> query = entityManager.createQuery("SELECT h FROM Hospital h WHERE h.speciality LIKE :speciality", Hospital.class);
        query.setParameter("speciality", "%" + speciality + "%");
        return query.getResultList();
    }

    @Override
    public List<Hospital> findByAvailableBedsAndBySpecialities(String speciality) {
        TypedQuery<Hospital> query = entityManager.createQuery("SELECT h FROM Hospital h WHERE h.available_beds > 0 AND h.speciality LIKE :speciality", Hospital.class);
        query.setParameter("speciality", "%" + speciality + "%");
        return query.getResultList();
    }

    @Override
    public List<String> findAllSpecialities() {
        TypedQuery<String> query = entityManager.createQuery("SELECT h.speciality FROM Hospital h", String.class);
        List<String> specialitiesList = query.getResultList();
        return specialitiesList.stream()
                .flatMap(specialities -> Stream.of(specialities.split("/")))
                .map(String::trim)
                .distinct()
                .collect(Collectors.toList());
    }

}
