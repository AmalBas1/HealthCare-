package com.healthcare.medical_system.service;

import com.healthcare.medical_system.dto.RendezVousDTO;
import com.healthcare.medical_system.entity.Medecin;
import com.healthcare.medical_system.entity.Patient;
import com.healthcare.medical_system.entity.RendezVous;
import com.healthcare.medical_system.entity.StatutRendezVous;
import com.healthcare.medical_system.mapper.MedecinMapper;
import com.healthcare.medical_system.mapper.RendezVousMapper;
import com.healthcare.medical_system.repository.MedecinRepository;
import com.healthcare.medical_system.repository.PatientRepository;
import com.healthcare.medical_system.repository.RendezVousRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RendezVousService {
    private final RendezVousRepository rdvRepo;
    private final RendezVousMapper rdvMapper;
    private final PatientRepository patientRepository;
    private final MedecinRepository medecinRepository;
    private final MedecinMapper medecinMapper;


    @Transactional
    public RendezVousDTO creerRendezVous(RendezVousDTO rdvDTO){
        Patient patient = patientRepository.findById(rdvDTO.getPatientId()).orElseThrow(()->new RuntimeException("patient non trouvé"));
        Medecin medecin = medecinRepository.findById(rdvDTO.getMedecinId()).orElseThrow(()->new RuntimeException("medecin non trouvé"));
        RendezVous rdv = rdvMapper.toEntity(rdvDTO);
        rdv.setPatient(patient);
        rdv.setMedecin(medecin);
        rdv.setStatut(StatutRendezVous.PLANIFIE);
        RendezVous savedRdv = rdvRepo.save(rdv);
        return rdvMapper.toDTO(savedRdv);
    }

    @Transactional
    public RendezVousDTO modifierRendezVous(Long id, RendezVousDTO rdvDTO){
        RendezVous rdv = rdvRepo.findById(id).orElseThrow(()->new RuntimeException("rendez-vous avec l'id: "+id+" n'existe pas'"));
        Patient patient = patientRepository.findById(rdvDTO.getPatientId()).orElseThrow(()->new RuntimeException("patient non trouvé"));
        Medecin medecin = medecinRepository.findById(rdvDTO.getMedecinId()).orElseThrow(()->new RuntimeException("medecin non trouvé'"));
        rdvMapper.updateEntityFromDto(rdvDTO, rdv);
        rdv.setMedecin(medecin);
        rdv.setPatient(patient);
        RendezVous savedRdv = rdvRepo.save(rdv);
        return rdvMapper.toDTO(savedRdv);
    }

    @Transactional
    public Page<RendezVousDTO> listerRendezVous(int page, int size){
        Pageable pageable = PageRequest.of(page,size);
       Page<RendezVous> rdv= rdvRepo.findAll(pageable);
        return rdv.map(rdvMapper::toDTO);
    }

    @Transactional
    public Page<RendezVousDTO> rechercherParPatient(Long id, int page, int size){
        Pageable pageable = PageRequest.of(page,size);
       Page<RendezVous> rdvPatient = rdvRepo.findByPatientId(id,pageable);
        return rdvPatient.map(rdvMapper::toDTO);
    }

    @Transactional
    public Page<RendezVousDTO> rechercherParMedecin(Long id, int page, int size){
        Pageable pageable = PageRequest.of(page,size);
        Page<RendezVous> rdvMedecin = rdvRepo.findByMedecinId(id,pageable);
        return rdvMedecin.map(rdvMapper::toDTO);
    }

    @Transactional
    public RendezVousDTO annulerRendezVous(Long id){
        RendezVous rdv = rdvRepo.findById(id).orElseThrow(()->new RuntimeException("rendez-vous avec l'id: "+id+" n'existe pas'"));
        rdv.setStatut(StatutRendezVous.ANNULE);
        RendezVous rdvSaved = rdvRepo.save(rdv);
        return rdvMapper.toDTO(rdvSaved);
    }
}
