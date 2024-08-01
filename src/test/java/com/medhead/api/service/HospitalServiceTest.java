package com.medhead.api.service;

import com.medhead.api.model.Hospital;
import com.medhead.api.repository.HospitalRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HospitalServiceTest {

    @Mock
    private HospitalRepository hospitalRepository;

    @InjectMocks
    private HospitalService hospitalService;

    private Hospital hospital1;
    private Hospital hospital2;

    @BeforeEach
    void setUp() {
        hospital1 = new Hospital();
        hospital1.setId(1);
        hospital1.setName("Bridgewater Hospital");
        hospital1.setLatitude(51.5074f); // London coordinates
        hospital1.setLongitude(-0.1278f);
        hospital1.setSpeciality("oral and maxillo-facial surgery, paediatric dentistry, gastroenterology");

        hospital2 = new Hospital();
        hospital2.setId(2);
        hospital2.setName("Nuffield Health");
        hospital2.setLatitude(53.483959f); // Manchester coordinates
        hospital2.setLongitude(-2.244644f);
        hospital2.setSpeciality("special care dentistry, emergency medicine, dental and maxillofacial radiology, allergy, restorative dentistry");
    }

    @AfterEach
    void tearDown() {
        hospital1 = null;
        hospital2 = null;
    }

    @Test
    public void testGetAllHospital() {
        when(hospitalRepository.findAll()).thenReturn(List.of(hospital1, hospital2));

        Iterable<Hospital> hospitals = hospitalService.getAllHospital();

        assertNotNull(hospitals);
        List<Hospital> hospitalList = (List<Hospital>) hospitals;
        assertFalse(hospitalList.isEmpty());
        assertEquals(2, hospitalList.size());
        assertEquals("Bridgewater Hospital", hospitalList.get(0).getName());
        assertEquals("Nuffield Health", hospitalList.get(1).getName());
    }

    @Test
    public void testSearchHospitalsWithResults() {
        when(hospitalRepository.findBySpeciality("cardiology")).thenReturn(List.of(hospital1));

        List<Hospital> hospitals = hospitalService.searchHospitals("cardiology", null, null, null, null);

        assertNotNull(hospitals);
        assertFalse(hospitals.isEmpty());
        assertEquals(1, hospitals.size());
        assertEquals("Bridgewater Hospital", hospitals.get(0).getName());
    }

    @Test
    public void testSearchHospitalsNoResults() {
        lenient().when(hospitalRepository.findAll()).thenReturn(List.of(hospital1, hospital2));

        List<Hospital> hospitals = hospitalService.searchHospitals("cardiologie", null, null, null, null);

        assertNotNull(hospitals);
        assertTrue(hospitals.isEmpty());
    }

    @Test
    public void testSearchHospitalsWithAvailableBeds() {
        when(hospitalRepository.findByAvailableBeds()).thenReturn(List.of(hospital1));

        List<Hospital> hospitals = hospitalService.searchHospitals(null, true, null, null, null);

        assertNotNull(hospitals);
        assertFalse(hospitals.isEmpty());
        assertEquals(1, hospitals.size());
        assertEquals("Bridgewater Hospital", hospitals.get(0).getName());
    }

    @Test
    public void testSearchHospitalsWithLocation() {
        when(hospitalRepository.findAll()).thenReturn(List.of(hospital1, hospital2));

        List<Hospital> hospitals = hospitalService.searchHospitals(null, null, 51.509865f, -0.118092f, 10);

        assertNotNull(hospitals);
        assertFalse(hospitals.isEmpty());
        assertEquals(1, hospitals.size());
        assertEquals("Bridgewater Hospital", hospitals.get(0).getName());
    }

    @Test
    public void testSearchHospitalsWithSpecialityAndLocation() {
        when(hospitalRepository.findBySpeciality("oral and maxillo-facial surgery")).thenReturn(List.of(hospital1));

        List<Hospital> hospitals = hospitalService.searchHospitals("oral and maxillo-facial surgery", null, 51.509865f, -0.118092f, 10);

        assertNotNull(hospitals);
        assertFalse(hospitals.isEmpty());
        assertEquals(1, hospitals.size());
        assertEquals("Bridgewater Hospital", hospitals.get(0).getName());
    }
}
