package com.healthcare.medical_system.controller;

import com.healthcare.medical_system.dto.MedecinDTO;

import com.healthcare.medical_system.service.MedecinService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/medecins")
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
    public ResponseEntity<MedecinDTO> modifierMedecin(@Valid @PathVariable Long id,  @RequestBody MedecinDTO medecinDTO){
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
    @Operation(summary = "liste de tous les médecins")
    public ResponseEntity<List<MedecinDTO>> listerMedecins(){
        List<MedecinDTO> medecins = medecinService.listerMedecins();
        return ResponseEntity.ok(medecins);
    }


}
