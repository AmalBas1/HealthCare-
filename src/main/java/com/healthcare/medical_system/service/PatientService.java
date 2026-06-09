package com.healthcare.medical_system.service;

import com.healthcare.medical_system.dto.PatientDTO;
import com.healthcare.medical_system.entity.Patient;
import com.healthcare.medical_system.mapper.PatientMapper;
import com.healthcare.medical_system.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @Transactional
    @CacheEvict(value = "patients",allEntries = true)
    public PatientDTO ajouterPatient(PatientDTO patientDTO){
        Patient patient= patientMapper.toEntity(patientDTO);
        Patient savedPatient = patientRepository.save(patient);
        return patientMapper.toDTO(savedPatient);
    }

    @Transactional
    @CacheEvict(value = "patients",allEntries = true)
    public PatientDTO ModifierPatient(Long id, PatientDTO patientDTO){
        Patient patient = patientRepository.findById(id).orElseThrow(()->new RuntimeException("patient non trouvé"));
        verifierAccesPatient(patient);
        patientMapper.updateEntityFromDTO(patientDTO,patient);
        Patient updatedPatient = patientRepository.save(patient);
        return patientMapper.toDTO(updatedPatient);

    }

    @Transactional
    @CacheEvict(value = "patients",allEntries = true)
    public void supprimerPatient(Long id){
        Patient patient = patientRepository.findById(id).orElseThrow(()->new RuntimeException("patient non trouvé"));
        patientRepository.delete(patient);
    }

    @Transactional
    @Cacheable(value="patients")
    public Page<PatientDTO> listerPatients(int page, int size,String sort ){
        Pageable pageable = PageRequest.of(page,size,Sort.Direction.fromString(sort), "nom");
        Page<Patient> patientPage = patientRepository.findAll(pageable);
        return patientPage.map(patientMapper::toDTO);

    }

    @Transactional
    public PatientDTO consulterPatient(Long id){
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new RuntimeException("patient non trouvé"));
        verifierAccesPatient(patient);
        return patientMapper.toDTO(patient);
    }

    @Transactional
    public Page<PatientDTO> rechercherPatientsParNom(String nom, int page, int size, String sortDir) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromString(sortDir), "nom");
        Page<Patient> patientPage = patientRepository.findByNomContainingIgnoreCase(nom, pageable);
        return patientPage.map(patientMapper::toDTO);
    }

    private void verifierAccesPatient(Patient patient){
        if (hasRole("PATIENT") && (patient.getUser() == null || !patient.getUser().getUsername().equals(usernameConnecte()))) {
            throw new AccessDeniedException("Acces refuse");
        }
    }

    private String usernameConnecte(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    private boolean hasRole(String role){
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_" + role));
    }
}

