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
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@Transactional
class RendezVousServiceTest {
    @Autowired
    private RendezVousService rdvService;
    @Autowired
    private RendezVousRepository rdvRepo;
    @Autowired
    private MedecinRepository medecinRepo;
    @Autowired
    private PatientRepository patientRepo;


    @Test
    void creerRendezVous(){
        String uniqueId = UUID.randomUUID().toString();
        Patient p = patientRepo.save( new Patient(null, "bass", "amal", "amal" + uniqueId + "@email.com","0600000000", LocalDate.of(1990,10,10)));
        Medecin m =medecinRepo.save( new Medecin(null, "fatima","dentiste","fatima" + uniqueId + "@email.com","060000001"));

        RendezVousDTO dto = new RendezVousDTO();
        dto.setMedecinId(m.getId());
        dto.setPatientId(p.getId());
        dto.setDateRendezVous(LocalDateTime.now().plusDays(2));


        RendezVousDTO resultat = rdvService.creerRendezVous(dto);

        assertNotNull(resultat.getId());
        assertEquals(p.getId(),resultat.getPatientId());
        assertEquals(m.getId(), resultat.getMedecinId());
    }

    @Test
    void modifierRendezVous(){
        String uniqueId = UUID.randomUUID().toString();
        Patient p = patientRepo.save( new Patient(null, "bass", "amal", "amal" + uniqueId + "@email.com","0600000000", LocalDate.of(1990,10,10)));
        Medecin m =medecinRepo.save( new Medecin(null, "fatimaa","dentiste","fatima" + uniqueId + "@email.com","060000001"));
        RendezVous rdv = rdvRepo.save(new RendezVous(null, LocalDateTime.now().plusDays(3), StatutRendezVous.PLANIFIE,p, m ));


        RendezVousDTO dto = new RendezVousDTO();
        dto.setDateRendezVous(rdv.getDateRendezVous());
        dto.setStatut(StatutRendezVous.ANNULE);
        dto.setMedecinId(m.getId());
        dto.setPatientId(p.getId());


        RendezVousDTO resultat = rdvService.modifierRendezVous(rdv.getId(),dto);

        assertNotNull(resultat.getId());
        assertEquals(rdv.getId(), resultat.getId());
        assertEquals(StatutRendezVous.ANNULE, resultat.getStatut());

    }

    @Test
    void listerRendezVous(){
        String uniqueId = UUID.randomUUID().toString();
        Patient p = patientRepo.save( new Patient(null, "bass", "amal", "amal" + uniqueId + "@email.com","0600000000", LocalDate.of(1990,10,10)));
        Medecin m =medecinRepo.save( new Medecin(null, "fatimaa","dentiste","fatima" + uniqueId + "@email.com","060000001"));
        rdvRepo.save(new RendezVous(null, LocalDateTime.now().plusDays(3), StatutRendezVous.PLANIFIE,p, m ));
        rdvRepo.save(new RendezVous(null, LocalDateTime.now().plusDays(2), StatutRendezVous.CONFIRME,p, m ));

        List<RendezVousDTO> resultats = rdvService.listerRendezVous();

        assertNotNull(resultats,"la liste ne doit pas être nulle ");
        assertTrue(resultats.size()>=2, "la liste doit contenir plus de 2 rendez-vous");
        assertNotNull(resultats.get(0).getId(),"l'id ne doit pas être null");
    }

    @Test
    void rechercherParPatient(){
        String uniqueId = UUID.randomUUID().toString();
        Patient p = patientRepo.save( new Patient(null, "bass", "amal", "amal" + uniqueId + "@email.com","0600000000", LocalDate.of(1990,10,10)));
        Medecin m =medecinRepo.save( new Medecin(null, "fatimaa","dentiste","fatima" + uniqueId + "@email.com","060000001"));
        rdvRepo.save(new RendezVous(null, LocalDateTime.now().plusDays(3), StatutRendezVous.PLANIFIE,p, m ));


        List<RendezVousDTO> resultat = rdvService.rechercherParPatient(p.getId());

        assertNotNull(resultat);
        assertNotNull(resultat.get(0).getId());
        assertEquals(resultat.get(0).getPatientId(), p.getId());

    }

    @Test
    void rechercherParMedecin(){
        String uniqueId = UUID.randomUUID().toString();
        Patient p = patientRepo.save( new Patient(null, "bass", "amal", "amal" + uniqueId + "@email.com","0600000000", LocalDate.of(1990,10,10)));
        Medecin m =medecinRepo.save( new Medecin(null, "fatimaa","dentiste","fatima" + uniqueId + "@email.com","060000001"));
        rdvRepo.save(new RendezVous(null, LocalDateTime.now().plusDays(3), StatutRendezVous.PLANIFIE,p, m ));

        List<RendezVousDTO> resultat = rdvService.rechercherParMedecin(m.getId());


        assertNotNull(resultat);
        assertNotNull(resultat.get(0).getId());
        assertEquals(resultat.get(0).getMedecinId(), m.getId());
    }

    @Test
    void annulerRendezVous(){
        String uniqueId = UUID.randomUUID().toString();
        Patient p = patientRepo.save( new Patient(null, "bass", "amal", "amal" + uniqueId + "@email.com","0600000000", LocalDate.of(1990,10,10)));
        Medecin m =medecinRepo.save( new Medecin(null, "fatimaa","dentiste","fatima" + uniqueId + "@email.com","060000001"));
        RendezVous rdv = rdvRepo.save(new RendezVous(null, LocalDateTime.now().plusDays(3), StatutRendezVous.PLANIFIE,p, m ));


        RendezVousDTO resultat = rdvService.annulerRendezVous(rdv.getId());

        assertNotNull(resultat);
        assertEquals(rdv.getId(), resultat.getId());
        assertEquals(resultat.getStatut(), StatutRendezVous.ANNULE);

    }


}
