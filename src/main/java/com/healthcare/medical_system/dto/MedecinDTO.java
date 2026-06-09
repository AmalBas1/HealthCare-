package com.healthcare.medical_system.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MedecinDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    @NotBlank(message = "le nom est obligatoire")
    private String nom;
    @NotBlank(message = "la spécialite est obligatoire")
    private String specialite;
    @Email(message = "le format de l'email est invalide")
    @NotBlank(message = "l'email est obligatoire")
    private String email;
    private String telephone;
    private Long userId;
}
