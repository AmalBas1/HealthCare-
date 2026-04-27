package com.healthcare.medical_system.service;

import com.healthcare.medical_system.dto.DossierMedicalDTO;
import com.healthcare.medical_system.entity.DossierMedical;
import com.healthcare.medical_system.entity.Patient;
import com.healthcare.medical_system.mapper.DossierMedicalMapper;
import com.healthcare.medical_system.repository.DossierMedicalRepository;
import com.healthcare.medical_system.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DossierMedicalService {
    private final DossierMedicalRepository dossierRepo;
    private final DossierMedicalMapper dossierMapper;
    private final PatientRepository patientRepository;

    @Transactional
    public DossierMedicalDTO creerDossierMedical (DossierMedicalDTO dossierMedicalDTO){
        Patient patient = patientRepository.findById(dossierMedicalDTO.getPatientId()).orElseThrow(()->new RuntimeException("patient non trouvé"));
        DossierMedical dossier = dossierMapper.toEntity(dossierMedicalDTO);
        dossier.setPatient(patient);
        DossierMedical savedDossier = dossierRepo.save(dossier);
        return dossierMapper.toDTO(savedDossier);
    }

    @Transactional
    public DossierMedicalDTO ajouterDiagnostic(Long id, String diagnostic){
        DossierMedical dossier = dossierRepo.findById(id).orElseThrow(()-> new RuntimeException("dossier introuvable"));
        dossier.setDiagnostic(diagnostic);
        DossierMedical savedDossier = dossierRepo.save(dossier);
        return dossierMapper.toDTO(savedDossier);
    }

    @Transactional
    public DossierMedicalDTO ajouterObservation(Long id, String observation){
        DossierMedical dossier = dossierRepo.findById(id).orElseThrow(()->new RuntimeException("dossier médical non trouvé"));
        dossier.setObservation(observation);
        DossierMedical savedDossier = dossierRepo.save(dossier);
        return dossierMapper.toDTO(savedDossier);
    }

    @Transactional
    public DossierMedicalDTO ConsulterDossierMedical(Long id){
        DossierMedical dossier = dossierRepo.findById(id).orElseThrow(()->new RuntimeException("dossier non trouvé"));
        return dossierMapper.toDTO(dossier);
    }

}
