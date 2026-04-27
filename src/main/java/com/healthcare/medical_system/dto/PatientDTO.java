package com.healthcare.medical_system.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {

    private Long id;
    @NotBlank(message = "le nom est obligatoire")
    private String nom;
    @NotBlank(message = "le prénom est obligatoire")
    private String prenom;
    @Email(message = "le format de l'email est invalide")
    @NotBlank(message = "l'email est obligatoire")
    private String email;
    private String telephone;
    @Past(message = "la date de naissance doit être au passé")
    @NotNull(message = "la date de naissance est obligatoire")
    private LocalDate dateNaissance;
}
