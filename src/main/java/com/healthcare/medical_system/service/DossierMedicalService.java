package com.healthcare.medical_system.service;

import com.healthcare.medical_system.dto.DossierMedicalDTO;
import com.healthcare.medical_system.entity.DossierMedical;
import com.healthcare.medical_system.entity.Patient;
import com.healthcare.medical_system.mapper.DossierMedicalMapper;
import com.healthcare.medical_system.repository.DossierMedicalRepository;
import com.healthcare.medical_system.repository.PatientRepository;
import com.healthcare.medical_system.repository.RendezVousRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DossierMedicalService {
    private final DossierMedicalRepository dossierRepo;
    private final DossierMedicalMapper dossierMapper;
    private final PatientRepository patientRepository;
    private final RendezVousRepository rendezVousRepository;

    @Transactional
    public DossierMedicalDTO creerDossierMedical (DossierMedicalDTO dossierMedicalDTO){
        Patient patient = patientRepository.findById(dossierMedicalDTO.getPatientId()).orElseThrow(()->new RuntimeException("patient non trouvé"));
        DossierMedical dossier = dossierMapper.toEntity(dossierMedicalDTO);
        dossier.setPatient(patient);
        DossierMedical savedDossier = dossierRepo.save(dossier);
        return dossierMapper.toDTO(savedDossier);
    }

    @Transactional
    public Page<DossierMedicalDTO> listerDossiersMedicaux(int page, int size){
        if (!hasRole("ADMIN")) {
            throw new AccessDeniedException("Accès refusé : Vous n'avez pas l'autorisation de l'accès global.");
        }
        Pageable pageable = PageRequest.of(page, size);
        Page<DossierMedical> dossiers = dossierRepo.findAll(pageable);
        return dossiers.map(dossierMapper::toDTO);
    }

    @Transactional
    public DossierMedicalDTO ajouterDiagnostic(Long id, String diagnostic){
        DossierMedical dossier = dossierRepo.findById(id).orElseThrow(()-> new RuntimeException("dossier introuvable"));
        verifierAccesDossier(dossier);
        dossier.setDiagnostic(diagnostic);
        DossierMedical savedDossier = dossierRepo.save(dossier);
        return dossierMapper.toDTO(savedDossier);
    }

    @Transactional
    public DossierMedicalDTO ajouterObservation(Long id, String observation){
        DossierMedical dossier = dossierRepo.findById(id).orElseThrow(()->new RuntimeException("dossier médical non trouvé"));
        verifierAccesDossier(dossier);
        dossier.setObservation(observation);
        DossierMedical savedDossier = dossierRepo.save(dossier);
        return dossierMapper.toDTO(savedDossier);
    }

    @Transactional
    public DossierMedicalDTO consulterDossierParPatientId(Long patientId) {
        DossierMedical dossier = dossierRepo.findByPatientId(patientId).orElseThrow(() -> new RuntimeException("Dossier médical introuvable pour ce patient."));

        verifierAccesDossier(dossier);

        return dossierMapper.toDTO(dossier);
    }

    private void verifierAccesDossier(DossierMedical dossier) {
        if (dossier == null || dossier.getPatient() == null) {
            throw new AccessDeniedException("Accès refusé : Informations du dossier incomplètes.");
        }

        if (hasRole("PATIENT")) {
            if (dossier.getPatient().getUser() == null ||
                    !dossier.getPatient().getUser().getUsername().equals(usernameConnecte())) {
                throw new AccessDeniedException("Accès refusé : Ce dossier ne vous appartient pas.");
            }
        }

        if (hasRole("MEDECIN")) {
            boolean aUnRendezVous = rendezVousRepository.existsByPatientIdAndMedecinUserUsername(
                    dossier.getPatient().getId(),
                    usernameConnecte()
            );
            if (!aUnRendezVous) {
                throw new AccessDeniedException("Accès refusé : Vous n'avez aucun rendez-vous avec ce patient.");
            }
        }

    }

    private String usernameConnecte() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    private boolean hasRole(String role) {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_" + role));
    }
}
