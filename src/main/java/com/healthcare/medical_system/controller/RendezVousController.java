package com.healthcare.medical_system.controller;

import com.healthcare.medical_system.dto.RendezVousDTO;
import com.healthcare.medical_system.service.RendezVousService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/appointments")
public class RendezVousController {
    private final RendezVousService rdvService;


    @PostMapping
    @Operation(summary = "ajouter un rendez_vous")
    public ResponseEntity<RendezVousDTO> creerRendezVous(@Valid @RequestBody RendezVousDTO rdvDTO) {
        RendezVousDTO rdvAjoute = rdvService.creerRendezVous(rdvDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(rdvAjoute);
    }

    @PutMapping("/{id}")
    @Operation(summary = "modifier un rendez-vous")
    public ResponseEntity<RendezVousDTO> modifierRendezVous(@Valid @PathVariable Long id, @RequestBody RendezVousDTO rdvDTO) {
        RendezVousDTO updatedRdv = rdvService.modifierRendezVous(id, rdvDTO);
        return ResponseEntity.ok(updatedRdv);
    }

    @GetMapping
    @Operation(summary = "liste de tous les rendez-vous")
    public ResponseEntity<List<RendezVousDTO>> listerRendezVous() {
        List<RendezVousDTO> rdv = rdvService.listerRendezVous();
        return ResponseEntity.ok(rdv);
    }

    @GetMapping("/patient/{patientId}")
    @Operation(summary = "liste de tous les rendez_vous d'un patient")
    public ResponseEntity<List<RendezVousDTO>> rechercherParPatient(@PathVariable Long patientId) {
        List<RendezVousDTO> rdvPatient = rdvService.rechercherParPatient(patientId);
        return ResponseEntity.ok(rdvPatient);
    }

    @GetMapping("/medecin/{medecinId}")
    @Operation(summary = "liste de tous les rendez_vous d'un médecin")
    public ResponseEntity<List<RendezVousDTO>> rechercherParMedecin(@PathVariable Long medecinId) {
        List<RendezVousDTO> rdvMedecin = rdvService.rechercherParMedecin(medecinId);
        return ResponseEntity.ok(rdvMedecin);
    }
    @PostMapping("/{id}/annuler")
    @Operation(summary = "annuler un rendez-vous")
    public ResponseEntity<RendezVousDTO> annulerRendezVous(@PathVariable Long id){
        RendezVousDTO rdvAnnule = rdvService.annulerRendezVous(id);
        return ResponseEntity.ok(rdvAnnule);
    }
}