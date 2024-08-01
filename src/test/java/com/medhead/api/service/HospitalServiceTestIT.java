package com.medhead.api.service;

import com.medhead.api.model.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class HospitalServiceTestIT {

    @Autowired
    private HospitalService hospitalService;

    @Test
    @Transactional(readOnly = true)
    public void testGetAllHospital() {
        List<Hospital> hospitals = (List<Hospital>) hospitalService.getAllHospital();

        assertNotNull(hospitals);
        assertFalse(hospitals.isEmpty());
        assertEquals(1290, hospitals.size());
    }

    @Test
    @Transactional(readOnly = true)
    public void testSearchHospitalsWithResults() {
        List<Hospital> hospitals = hospitalService.searchHospitals("cardiology", null, null, null, null);

        assertNotNull(hospitals);
        assertFalse(hospitals.isEmpty());
    }

    @Test
    @Transactional(readOnly = true)
    public void testSearchHospitalsNoResults() {
        List<Hospital> hospitals = hospitalService.searchHospitals("neurology", null, null, null, null);

        assertNotNull(hospitals);
        assertTrue(hospitals.isEmpty());
    }

    @Test
    @Transactional(readOnly = true)
    public void testSearchHospitalsWithAvailableBeds() {
        List<Hospital> hospitals = hospitalService.searchHospitals(null, true, null, null, null);

        assertNotNull(hospitals);
        assertFalse(hospitals.isEmpty());
    }

    @Test
    @Transactional(readOnly = true)
    public void testSearchHospitalsWithLocation() {
        List<Hospital> hospitals = hospitalService.searchHospitals(null, null, 51.509865f, -0.118092f, 10);

        assertNotNull(hospitals);
        assertFalse(hospitals.isEmpty());
    }

    @Test
    @Transactional(readOnly = true)
    public void testSearchHospitalsWithSpecialityAndLocation() {
        List<Hospital> hospitals = hospitalService.searchHospitals("oral and maxillo-facial surgery", null, 51.509865f, -0.118092f, 10);

        assertNotNull(hospitals);
        assertFalse(hospitals.isEmpty());
    }

    @Test
    @Transactional(readOnly = true)
    public void testSearchHospitalsWithMultipleCriteria() {
        List<Hospital> hospitals = hospitalService.searchHospitals("dentistry", true, 51.509865f, -0.118092f, 20);

        assertNotNull(hospitals);
        assertFalse(hospitals.isEmpty());
    }
}
