package com.healthcare.medical_system.repository;

import com.healthcare.medical_system.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PatientRepository extends JpaRepository<Patient, Long>{
    Page<Patient> findByNomContainingIgnoreCase(String nom, Pageable pageable);
}
