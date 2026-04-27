package com.healthcare.medical_system.repository;

import com.healthcare.medical_system.entity.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {
    List<RendezVous> findByPatientId(Long id);
    List<RendezVous> findByMedecinId(Long id);
}
