package com.healthcare.medical_system.service;

import com.healthcare.medical_system.dto.PatientDTO;
import com.healthcare.medical_system.entity.Patient;
import com.healthcare.medical_system.repository.PatientRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class PatientServiceTest {
    @Autowired
    private PatientService patientService;
    @Autowired
    private PatientRepository patientRepo;

    @Test
    void consulterPatient() {
        String uniqueId = UUID.randomUUID().toString();
        Patient p = patientRepo.save( new Patient(null, "bas", "amal", "amal" + uniqueId + "@email.com","0600000000", LocalDate.of(1990,10,10)));

        PatientDTO resultat = patientService.consulterPatient(p.getId());

        assertNotNull(resultat);
        assertEquals(resultat.getId(), p.getId());
        assertEquals(resultat.getPrenom(),"amal");
        assertEquals(resultat.getTelephone(),"0600000000");

    }
}