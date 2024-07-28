package com.medhead.api.service;

import com.medhead.api.model.Hospital;
import com.medhead.api.repository.HospitalRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HospitalServiceTest {

    @Mock
    private HospitalRepository hospitalRepository;

    @InjectMocks
    private HospitalService hospitalService;

    @Test
    public void testGetAllHospital() {
        when(hospitalRepository.findAll()).thenReturn(List.of(new Hospital()));
        Iterable<Hospital> hospitals = hospitalService.getAllHospital();
        assertNotNull(hospitals);
        assertFalse(((List<Hospital>) hospitals).isEmpty());
    }

    @Test
    public void testSearchHospitals() {
        when(hospitalRepository.findAll()).thenReturn(List.of(new Hospital()));
        List<Hospital> hospitals = hospitalService.searchHospitals(null, null, null, null, null);
        assertNotNull(hospitals);
        assertFalse(hospitals.isEmpty());
    }
}
