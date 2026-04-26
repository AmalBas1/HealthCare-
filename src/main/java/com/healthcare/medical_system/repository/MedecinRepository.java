package com.healthcare.medical_system.repository;

import com.healthcare.medical_system.entity.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedecinRepository extends JpaRepository<Medecin, Long> {
}
