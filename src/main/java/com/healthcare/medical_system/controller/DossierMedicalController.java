package com.healthcare.medical_system.controller;

import com.healthcare.medical_system.dto.DossierMedicalDTO;
import com.healthcare.medical_system.service.DossierMedicalService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dossiers-medicaux")
public class DossierMedicalController {
    private final DossierMedicalService dossierService;


    @PostMapping
    @Operation(summary = "créer un dossier médical")
    public ResponseEntity<DossierMedicalDTO> creerDossierMedical(@RequestBody DossierMedicalDTO dossierDTO){
        DossierMedicalDTO dossierAjoute = dossierService.creerDossierMedical(dossierDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(dossierAjoute);
    }

    @PostMapping("/{dossierId}/diagnostic")
    @Operation(summary = "ajouter un diagnostic à un dossier médical")
    public ResponseEntity<DossierMedicalDTO> ajouterDiagnostic(@PathVariable Long dossierId, @RequestBody String diagnostic){
        DossierMedicalDTO diagnosticAjoute = dossierService.ajouterDiagnostic(dossierId, diagnostic);
        return ResponseEntity.ok(diagnosticAjoute);
    }

    @PostMapping("/{dossierId}/observation")
    @Operation(summary = "ajouter une observation à un dossier médical")
    public ResponseEntity<DossierMedicalDTO> ajouterObservation(@PathVariable Long dossierId, @RequestBody String observation){
        DossierMedicalDTO observationAjoute = dossierService.ajouterObservation(dossierId, observation);
        return ResponseEntity.ok(observationAjoute);
    }

    @GetMapping("/{id}")
    @Operation(summary = "consulter un  dossier médical")
    public ResponseEntity<DossierMedicalDTO> ConsulterDossierMedical(@PathVariable Long id){
        DossierMedicalDTO dossier = dossierService.ConsulterDossierMedical(id);
        return ResponseEntity.ok(dossier);
    }


}
