package com.healthcare.medical_system.controller;

import com.healthcare.medical_system.dto.RendezVousDTO;
import com.healthcare.medical_system.entity.StatutRendezVous;
import com.healthcare.medical_system.service.RendezVousService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/appointments")
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
    public ResponseEntity<RendezVousDTO> modifierRendezVous( @PathVariable Long id, @Valid @RequestBody RendezVousDTO rdvDTO) {
        RendezVousDTO updatedRdv = rdvService.modifierRendezVous(id, rdvDTO);
        return ResponseEntity.ok(updatedRdv);
    }

    @GetMapping
    @Operation(summary = "liste paginée de tous les rendez-vous")
    public ResponseEntity<Page<RendezVousDTO>> listerRendezVous(@RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "5") int size,
                                                                @RequestParam(defaultValue = "asc") String sortDir) {
        Page<RendezVousDTO> rdv = rdvService.listerRendezVous(page,size,sortDir);
        return ResponseEntity.ok(rdv);
    }

    @GetMapping("/patient/{patientId}")
    @Operation(summary = "liste paginée de tous les rendez_vous d'un patient")
    public ResponseEntity<Page<RendezVousDTO>> rechercherParPatient(@PathVariable Long patientId,
                                                                    @RequestParam(defaultValue = "0") int page,
                                                                    @RequestParam(defaultValue = "5") int size) {
        Page<RendezVousDTO> rdvPatient = rdvService.rechercherParPatient(patientId,page,size);
        return ResponseEntity.ok(rdvPatient);
    }

    @GetMapping("/medecin/{medecinId}")
    @Operation(summary = "liste paginée de tous les rendez_vous d'un médecin")
    public ResponseEntity<Page<RendezVousDTO>> rechercherParMedecin(@PathVariable Long medecinId,
                                                                    @RequestParam(defaultValue = "0") int page,
                                                                    @RequestParam(defaultValue = "5") int size) {
        Page<RendezVousDTO> rdvMedecin = rdvService.rechercherParMedecin(medecinId,page,size);
        return ResponseEntity.ok(rdvMedecin);
    }
    @PostMapping("/{id}/annuler")
    @Operation(summary = "annuler un rendez-vous")
    public ResponseEntity<RendezVousDTO> annulerRendezVous(@PathVariable Long id){
        RendezVousDTO rdvAnnule = rdvService.annulerRendezVous(id);
        return ResponseEntity.ok(rdvAnnule);
    }
    @GetMapping("/search")
    @Operation(summary = "Recherche paginée et triée des rendez-vous par statut")
    public ResponseEntity<Page<RendezVousDTO>> rechercherRendezVousParStatut(
            @RequestParam StatutRendezVous statut,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "asc") String sortDir) {
        Page<RendezVousDTO> rdv = rdvService.rechercherRendezVousParStatut(statut, page, size, sortDir);
        return ResponseEntity.ok(rdv);
    }
}