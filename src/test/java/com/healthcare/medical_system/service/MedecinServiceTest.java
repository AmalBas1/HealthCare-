package com.healthcare.medical_system.service;

import com.healthcare.medical_system.dto.MedecinDTO;
import com.healthcare.medical_system.entity.Medecin;
import com.healthcare.medical_system.repository.MedecinRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class MedecinServiceTest {
    @Autowired
    private  MedecinService medecinService;
    @Autowired
    private MedecinRepository medecinRepo;


    @Test
    void modifierMedecin() {
        String uniqueId = UUID.randomUUID().toString();
        Medecin m =medecinRepo.save( new Medecin(null, "fatimaa","dentiste","fatima" + uniqueId + "@email.com","060000001"));

        MedecinDTO medecinModifie= new MedecinDTO();
        medecinModifie.setId(m.getId());
        medecinModifie.setEmail(m.getEmail());
        medecinModifie.setNom("alaoui");
        medecinModifie.setTelephone(m.getTelephone());
        medecinModifie.setSpecialite(m.getSpecialite());

        MedecinDTO resultat = medecinService.modifierMedecin(m.getId(),medecinModifie);

        assertNotNull(resultat);
        assertEquals(m.getId(), resultat.getId());
        assertEquals(resultat.getNom(), "alaoui");


    }
}