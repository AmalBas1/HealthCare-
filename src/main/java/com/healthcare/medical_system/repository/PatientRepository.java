package com.healthcare.medical_system.repository;

import com.healthcare.medical_system.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long>{
}
