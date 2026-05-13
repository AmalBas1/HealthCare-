package com.healthcare.medical_system.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor

public class LoginRequest {

    @NotBlank(message = "Le nom d'utilisateur ne peut pas être vide")
    private String username;
    @NotBlank(message = "Le mot de passe est obligatoire")
    private String password;
}
