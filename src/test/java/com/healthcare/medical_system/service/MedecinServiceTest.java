package com.healthcare.medical_system.service;

import com.healthcare.medical_system.dto.MedecinDTO;
import com.healthcare.medical_system.entity.Medecin;
import com.healthcare.medical_system.repository.MedecinRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
@ActiveProfiles("test")
@WithMockUser(username = "admin", roles = {"ADMIN"})
class MedecinServiceTest {
    @Autowired
    private  MedecinService medecinService;
    @Autowired
    private MedecinRepository medecinRepo;


    @Test
    void modifierMedecin() {
        Medecin m = medecinRepo.save(new Medecin(null, "Nom", "Spec", "email@test.com", "0600000000", null));

        MedecinDTO dto = new MedecinDTO();
        dto.setNom("NouveauNom");

        MedecinDTO res = medecinService.modifierMedecin(m.getId(), dto);
        assertEquals("NouveauNom", res.getNom());
    }
}