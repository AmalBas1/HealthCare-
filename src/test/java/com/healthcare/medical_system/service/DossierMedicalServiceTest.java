package com.healthcare.medical_system.service;

import com.healthcare.medical_system.dto.DossierMedicalDTO;
import com.healthcare.medical_system.entity.Patient;
import com.healthcare.medical_system.repository.PatientRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class DossierMedicalServiceTest {
    @Autowired
    DossierMedicalService dossierService;
    @Autowired
    PatientRepository patientRepo;

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void creerDossierMedical() {
        String uniqueId = UUID.randomUUID().toString();
        Patient p = patientRepo.save(new Patient(null, "bass", "amal",
                "amal" + uniqueId + "@email.com", "0600000000", LocalDate.of(1990, 10, 10), null));

        DossierMedicalDTO dossier = new DossierMedicalDTO();
        dossier.setPatientId(p.getId());

        DossierMedicalDTO resultat = dossierService.creerDossierMedical(dossier);

        assertNotNull(resultat);
        assertNotNull(resultat.getId());
        assertEquals(resultat.getPatientId(), p.getId());
    }
}