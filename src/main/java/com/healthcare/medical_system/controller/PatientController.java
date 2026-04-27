package com.healthcare.medical_system.controller;

import com.healthcare.medical_system.dto.PatientDTO;
import com.healthcare.medical_system.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.ServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;

    @PostMapping()
    @Operation(summary = "ajouter un patient")
    public ResponseEntity<PatientDTO> ajoutePatient(@Valid @RequestBody PatientDTO patientDTO){
        PatientDTO patientAjoute = patientService.ajouterPatient(patientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(patientAjoute);
    }

    @PutMapping("/{id}")
    @Operation(summary = "modifier patient")
    public ResponseEntity<PatientDTO> modifierPatient(@Valid @PathVariable Long id, @RequestBody PatientDTO patientDTO){
        PatientDTO updatedPatient = patientService.ModifierPatient(id, patientDTO);
        return ResponseEntity.ok(updatedPatient);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "supprimer patient")
    public ResponseEntity<Void> supprimerPatient(@PathVariable Long id){
        patientService.supprimerPatient(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "liste de tous les patients")
    public ResponseEntity<List<PatientDTO>> listerPatients(ServletRequest servletRequest){
        List<PatientDTO> patients = patientService.listerPatients();
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/{id}")
    @Operation(summary = "consulter patient par id")
    public ResponseEntity<PatientDTO> consulterPatient(@PathVariable Long id){
        PatientDTO patient = patientService.consulterPatient(id);
        return ResponseEntity.ok(patient);
    }


}
