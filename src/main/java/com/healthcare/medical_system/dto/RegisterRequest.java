package com.healthcare.medical_system.dto;

import com.healthcare.medical_system.entity.Role;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "Le nom d'utilisateur est obligatoire")
    private String username;
    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Format d'email invalide")
    private String email;
    @NotBlank(message = "Le mot de passe est obligatoire")
    @Size(min = 6, message = "Le mot de passe doit avoir au moins 6 caractères")
    private String password;
    @NotNull(message = "Le role est obligatoire(ADMIN, PATIENT, MEDECIN)")
    private Role role;


    private String nom;
    private String prenom;
    private String telephone;
    @Past(message = "la date de naissance doit être au passé")
    private LocalDate dateNaissance;
    private String specialite;

}
