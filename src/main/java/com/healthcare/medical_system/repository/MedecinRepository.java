package com.healthcare.medical_system.repository;

import com.healthcare.medical_system.entity.Medecin;
import com.healthcare.medical_system.entity.Patient;
import com.healthcare.medical_system.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface MedecinRepository extends JpaRepository<Medecin, Long> {
    Page<Medecin> findBySpecialiteContainingIgnoreCase(String specialite, Pageable pageable);
    boolean existsByIdAndUserUsername(Long id, String username);
    Optional<Medecin> findByUser(User user);
}
