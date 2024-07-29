package com.medhead.api.service;

import com.medhead.api.model.Hospital;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
public class HospitalServiceTestIT {

    @Autowired
    private HospitalService hospitalService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testGetAllHospital() {
        Iterable<Hospital> hospitals = hospitalService.getAllHospital();
        assertNotNull(hospitals);
        assertFalse(((List<Hospital>) hospitals).isEmpty());
    }

    @Test
    public void testSearchHospitals() {
        List<Hospital> hospitals = hospitalService.searchHospitals(null, null, null, null, null);
        assertNotNull(hospitals);
        assertFalse(hospitals.isEmpty());
    }
}
