package com.healthcare.medical_system.controller;

import com.healthcare.medical_system.dto.PatientDTO;
import com.healthcare.medical_system.entity.Patient;
import com.healthcare.medical_system.repository.DossierMedicalRepository;
import com.healthcare.medical_system.repository.PatientRepository;
import com.healthcare.medical_system.service.PatientService;
import com.healthcare.medical_system.service.PdfService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

//@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/patients")
public class PatientController {
    private final PatientService patientService;
    private final PdfService pdfService;
    private final PatientRepository patientRepo;

    @PostMapping()
    @Operation(summary = "ajouter un patient")
    public ResponseEntity<PatientDTO> ajoutePatient(@Valid @RequestBody PatientDTO patientDTO){
        PatientDTO patientAjoute = patientService.ajouterPatient(patientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(patientAjoute);
    }

    @PutMapping("/{id}")
    @Operation(summary = "modifier patient")
    public ResponseEntity<PatientDTO> modifierPatient( @PathVariable Long id, @Valid @RequestBody PatientDTO patientDTO){
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
    @Operation(summary = "liste paginée de tous les patients")
    public ResponseEntity<Page<PatientDTO>> listerPatients(@RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "5") int size,
                                                           @RequestParam(defaultValue = "asc") String sortDir){
        Page<PatientDTO> patients = patientService.listerPatients(page,size,sortDir);
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/{id}")
    @Operation(summary = "consulter patient par id")
    public ResponseEntity<PatientDTO> consulterPatient(@PathVariable Long id){
        PatientDTO patient = patientService.consulterPatient(id);
        return ResponseEntity.ok(patient);
    }
    @GetMapping("/me")
    public ResponseEntity<PatientDTO> getMyProfile(Principal principal) {
        String username = principal.getName();
        PatientDTO patient = patientService.getByUsername(username);
        return ResponseEntity.ok(patient);
    }
    @GetMapping("/search")
    @Operation(summary = "Recherche paginée et triée des patients par nom")
    public ResponseEntity<Page<PatientDTO>> rechercherPatientsParNom(
            @RequestParam String nom,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "asc") String sortDir) {
        Page<PatientDTO> patients = patientService.rechercherPatientsParNom(nom, page, size, sortDir);
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/export/pdf")
    public ResponseEntity<byte[]> downloadPdf() throws Exception {
        List<Patient> patients = patientRepo.findAll();
        byte[] pdfBytes = pdfService.generatePatientListPdf(patients);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "patients.pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }

    @GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> downloadPatientPdf(@PathVariable Long id) {
        try {
            Patient patient = patientRepo.findById(id).orElseThrow(() ->new RuntimeException("patient non trouvé"));

            byte[] pdfBytes = pdfService.generateMDossierMedicalPdf(patient);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);

            headers.setContentDispositionFormData("attachment", "dossier_" + patient.getNom() + ".pdf");

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
