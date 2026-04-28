package com.healthcare.medical_system.repository;

import com.healthcare.medical_system.entity.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {
    List<RendezVous> findByPatientId(Long id);
    List<RendezVous> findByMedecinId(Long id);
}
