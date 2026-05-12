package com.healthcare.medical_system.service;

import com.healthcare.medical_system.dto.RegisterRequest;
import com.healthcare.medical_system.entity.User;
import com.healthcare.medical_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public String register(RegisterRequest request){
        if (userRepo.existsByEmail(request.getEmail())){ throw  new RuntimeException("erreur: le nom d'utilisateur existe déjà");}


        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());


        userRepo.save(user);

        return "utilisateur enregistré avec succès!";

    }


}
