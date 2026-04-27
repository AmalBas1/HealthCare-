package com.healthcare.medical_system.dto;

import com.healthcare.medical_system.entity.Patient;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DossierMedicalDTO {
    private Long id;
    private String diagnostic;
    private String observation;
    @NotNull(message = "la date de création du dossier médical est obligatoire")
    private LocalDate dateCreation;
    @NotNull(message = "l'id du patient est obligatoire")
    private Long patientId;
}
