package com.healthcare.medical_system.repository;

import com.healthcare.medical_system.entity.DossierMedical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface DossierMedicalRepository extends JpaRepository<DossierMedical, Long> {
    List<DossierMedicalRepository> findByPatientId(Long id);
}
