package com.healthcare.medical_system.service;

import com.healthcare.medical_system.dto.PatientDTO;
import com.healthcare.medical_system.entity.Patient;
import com.healthcare.medical_system.repository.PatientRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
@WithMockUser(roles = "ADMIN")
class PatientServiceTest {
    @Autowired
    private PatientService patientService;
    @Autowired
    private PatientRepository patientRepo;

    @Test
    void consulterPatient() {
        Patient p = patientRepo.save(new Patient(null, "Nom", "Prenom", "test@test.com", "0600000000", LocalDate.now(), null));

        PatientDTO res = patientService.consulterPatient(p.getId());

        assertNotNull(res);
        assertEquals(p.getId(), res.getId());
    }
}