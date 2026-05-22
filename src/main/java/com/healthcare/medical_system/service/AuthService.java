package com.healthcare.medical_system.service;

import com.healthcare.medical_system.config.JwtUtils;
import com.healthcare.medical_system.dto.AuthResponse;
import com.healthcare.medical_system.dto.LoginRequest;
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
    private final JwtUtils jwtUtils;

    public AuthResponse register(RegisterRequest request){
        if (userRepo.existsByUsername(request.getUsername())){
            throw  new RuntimeException("l'utilisateur: "+request.getUsername()+ " est déjà enregistré");
        }
        if (userRepo.existsByEmail(request.getEmail())) {
            throw new RuntimeException("cet email est déjà utilisé!");
        }


        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());


        userRepo.save(user);

        String token = jwtUtils.genererToken(user.getUsername(), user.getRole());

        return new AuthResponse(token);

    }

    public AuthResponse login(LoginRequest request){
        User user = userRepo.findByUsername(request.getUsername()).orElseThrow(()-> new RuntimeException("utilisateur non trouvé"));
         if(passwordEncoder.matches(request.getPassword(), user.getPassword())){
             String token = jwtUtils.genererToken(user.getUsername(), user.getRole());
             return new AuthResponse(token);
         }else {
             throw new RuntimeException("password incorrect");
         }
    }


}
