package com.healthcare.medical_system.repository;

import com.healthcare.medical_system.entity.RendezVous;
import com.healthcare.medical_system.entity.StatutRendezVous;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {
    Page<RendezVous> findByPatientId(Long patientId, Pageable pageable);
    Page<RendezVous> findByMedecinId(Long medecinId, Pageable pageable);
    Page<RendezVous> findByStatut(StatutRendezVous statut, Pageable pageable);

}
