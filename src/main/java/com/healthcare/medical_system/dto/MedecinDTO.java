package com.healthcare.medical_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MedecinDTO {
    private Long id;
    private String nom;
    private String specialite;
    private String email;
    private String telephone;
}
