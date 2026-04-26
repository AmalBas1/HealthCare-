package com.healthcare.medical_system.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {

    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private LocalDate dateNaissance;
}
