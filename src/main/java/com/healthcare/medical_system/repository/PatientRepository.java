package com.healthcare.medical_system.repository;

import com.healthcare.medical_system.entity.Patient;
import com.healthcare.medical_system.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface PatientRepository extends JpaRepository<Patient, Long>{
    Page<Patient> findByNomContainingIgnoreCase(String nom, Pageable pageable);
    boolean existsByIdAndUserUsername(Long id, String username);
    Optional<Patient> findByUser(User user);}
