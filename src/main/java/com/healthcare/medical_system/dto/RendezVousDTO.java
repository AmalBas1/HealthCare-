package com.healthcare.medical_system.dto;

import com.healthcare.medical_system.entity.StatutRendezVous;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RendezVousDTO {
    private Long id;
    @NotNull(message = "la date et l'heure du rendez-vous sont obligatoires")
    @FutureOrPresent(message = "la date du rendez-vous doit-être au futur")
    private LocalDateTime dateRendezVous;
    private StatutRendezVous statut;
    @NotNull(message = "l'id du patient est obligatoire")
    private Long patientId;
    @NotNull(message = "l'id du patient est obligatoire")
    private Long medecinId;
}
