package com.healthcare.medical_system.service;


import com.healthcare.medical_system.dto.RendezVousDTO;
import com.healthcare.medical_system.entity.Medecin;
import com.healthcare.medical_system.entity.Patient;
import static org.junit.jupiter.api.Assertions.*;

import com.healthcare.medical_system.entity.RendezVous;
import com.healthcare.medical_system.entity.StatutRendezVous;
import com.healthcare.medical_system.repository.MedecinRepository;
import com.healthcare.medical_system.repository.PatientRepository;
import com.healthcare.medical_system.repository.RendezVousRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.security.test.context.support.WithMockUser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@Transactional
@WithMockUser(roles = "ADMIN")
class RendezVousServiceTest {
    @Autowired
    private RendezVousService rdvService;
    @Autowired
    private RendezVousRepository rdvRepo;
    @Autowired
    private MedecinRepository medecinRepo;
    @Autowired
    private PatientRepository patientRepo;

    Patient p;
    Medecin m;

    @BeforeEach
    void setup() {
        p = patientRepo.save(new Patient(null, "P", "P", "p@test.com", "0600", LocalDate.now(), null));
        m = medecinRepo.save(new Medecin(null, "M", "S", "m@test.com", "0600", null));
    }

    @Test
    void creerRendezVous() {
        RendezVousDTO dto = new RendezVousDTO();
        dto.setPatientId(p.getId());
        dto.setMedecinId(m.getId());
        dto.setDateRendezVous(LocalDateTime.now().plusDays(1));

        RendezVousDTO res = rdvService.creerRendezVous(dto);
        assertNotNull(res.getId());
    }


}
