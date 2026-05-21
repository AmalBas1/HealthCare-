package com.healthcare.medical_system.controller;

import com.healthcare.medical_system.dto.MedecinDTO;

import com.healthcare.medical_system.service.MedecinService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/medecins")
public class MedecinController {
    private final MedecinService medecinService;

    @PostMapping()
    @Operation(summary = "ajouter médecin")
    public ResponseEntity<MedecinDTO> ajouterMedecin(@Valid @RequestBody MedecinDTO medecinDTO){
        MedecinDTO medecinAjoute = medecinService.ajouterMedecin(medecinDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(medecinAjoute);
    }

    @PutMapping("/{id}")
    @Operation(summary = "modifier médecin")
    public ResponseEntity<MedecinDTO> modifierMedecin( @PathVariable Long id, @Valid @RequestBody MedecinDTO medecinDTO){
        MedecinDTO updatedMedecin = medecinService.modifierMedecin(id,medecinDTO);
        return ResponseEntity.ok(updatedMedecin);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "supprimer médecin")
    public ResponseEntity<Void> supprimerMedecin(@PathVariable Long id){
        medecinService.supprimerMedecin(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "liste paginée de tous les médecins")
    public ResponseEntity<Page<MedecinDTO>> listerMedecins(@RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "5") int size,
                                                           @RequestParam(defaultValue = "asc") String sortDir){
        Page<MedecinDTO> medecins = medecinService.listerMedecins(page,size, sortDir);
        return ResponseEntity.ok(medecins);
    }
    @GetMapping("/search")
    @Operation(summary = "Recherche paginée et triée des médecins par spécialité")
    public ResponseEntity<Page<MedecinDTO>> rechercherMedecinsParSpecialite(
            @RequestParam String specialite,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "asc") String sortDir) {
        Page<MedecinDTO> medecins = medecinService.rechercherMedecinsParSpecialite(specialite, page, size, sortDir);
        return ResponseEntity.ok(medecins);
    }

}
