package com.healthcare.medical_system.service;

import com.healthcare.medical_system.dto.PatientDTO;
import com.healthcare.medical_system.entity.Patient;
import com.healthcare.medical_system.mapper.PatientMapper;
import com.healthcare.medical_system.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @Transactional
    public PatientDTO ajouterPatient(PatientDTO patientDTO){
        Patient patient= patientMapper.toEntity(patientDTO);
        Patient savedPatient = patientRepository.save(patient);
        return patientMapper.toDTO(savedPatient);
    }

    @Transactional
    public PatientDTO ModifierPatient(Long id, PatientDTO patientDTO){
        Patient patient = patientRepository.findById(id).orElseThrow(()->new RuntimeException("patient non trouvé"));
        patientMapper.updateEntityFromDTO(patientDTO,patient);
        Patient updatedPatient = patientRepository.save(patient);
        return patientMapper.toDTO(updatedPatient);

    }

    @Transactional
    public void supprimerPatient(Long id){
        Patient patient = patientRepository.findById(id).orElseThrow(()->new RuntimeException("patient non trouvé"));
        patientRepository.delete(patient);
    }

    @Transactional
    public Page<PatientDTO> listerPatients(int page, int size,String sort ){
        Pageable pageable = PageRequest.of(page,size,Sort.Direction.fromString(sort), "nom");
        Page<Patient> patientPage = patientRepository.findAll(pageable);
        return patientPage.map(patientMapper::toDTO);

    }

    @Transactional
    public PatientDTO consulterPatient(Long id){
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new RuntimeException("patient non trouvé"));
        return patientMapper.toDTO(patient);
    }

    @Transactional
    public Page<PatientDTO> rechercherPatientsParNom(String nom, int page, int size, String sortDir) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromString(sortDir), "nom");
        Page<Patient> patientPage = patientRepository.findByNomContainingIgnoreCase(nom, pageable);
        return patientPage.map(patientMapper::toDTO);
    }

}

