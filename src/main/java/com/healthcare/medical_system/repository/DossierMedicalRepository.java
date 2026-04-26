package com.healthcare.medical_system.repository;

import com.healthcare.medical_system.entity.DossierMedical;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DossierMedicalRepository extends JpaRepository<DossierMedical, Long> {
    List<DossierMedicalRepository> findByPatientId(Long id);
}
