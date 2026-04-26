package com.healthcare.medical_system.dto;

import com.healthcare.medical_system.entity.StatutRendezVous;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RendezVousDTO {
    private Long id;
    private LocalDateTime dateRendezVous;
    private StatutRendezVous statut;
    private Long patientId;
    private Long medecinId;
}
