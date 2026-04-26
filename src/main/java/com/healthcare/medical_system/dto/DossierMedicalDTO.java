package com.healthcare.medical_system.dto;

import com.healthcare.medical_system.entity.Patient;
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
    private LocalDate dateCreation;
    private Long patientId;
}
