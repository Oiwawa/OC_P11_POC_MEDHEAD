package com.medhead.api.controller;

import com.medhead.api.model.Hospital;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class HospitalControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetAllHospitals() {
        ResponseEntity<Hospital[]> response = restTemplate.getForEntity("/hospital", Hospital[].class);
        Hospital[] hospitals = response.getBody();
        assertNotNull(hospitals);
        assertNotEquals(0, hospitals.length);
    }

    @Test
    public void testGetAllSpecialities() {
        ResponseEntity<String[]> response = restTemplate.getForEntity("/hospital/specialities", String[].class);
        String[] specialities = response.getBody();
        assertNotNull(specialities);
        assertNotEquals(0, specialities.length);
    }

    @Test
    public void testGetAllBySpeciality() {
        ResponseEntity<Hospital[]> response = restTemplate.getForEntity("/hospital/speciality/cardiology", Hospital[].class);
        Hospital[] hospitals = response.getBody();
        assertNotNull(hospitals);
        assertNotEquals(0, hospitals.length);
    }

    @Test
    public void testGetAllAvailableBedsHospitals() {
        ResponseEntity<Hospital[]> response = restTemplate.getForEntity("/hospital/available", Hospital[].class);
        Hospital[] hospitals = response.getBody();
        assertNotNull(hospitals);
        assertNotEquals(0, hospitals.length);
    }

    @Test
    public void testSearchHospitals() {
        String url = "/hospital/search?latInit=51.509865&longInit=-0.118092&distance=10";
        ResponseEntity<Hospital[]> response = restTemplate.getForEntity(url, Hospital[].class);
        Hospital[] hospitals = response.getBody();
        assertNotNull(hospitals);
        assertNotEquals(0, hospitals.length);
    }
}
