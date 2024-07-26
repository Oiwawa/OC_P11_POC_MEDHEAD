package com.medhead.api.repository;

import com.medhead.api.model.Hospital;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public List<Hospital> findBySpecialities(String speciality) {
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
}
