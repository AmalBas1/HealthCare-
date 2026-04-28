package com.healthcare.medical_system.repository;

import com.healthcare.medical_system.entity.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface MedecinRepository extends JpaRepository<Medecin, Long> {
}
