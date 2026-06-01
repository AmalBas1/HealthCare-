package com.healthcare.medical_system.service;

import com.healthcare.medical_system.config.JwtUtils;
import com.healthcare.medical_system.dto.AuthResponse;
import com.healthcare.medical_system.dto.LoginRequest;
import com.healthcare.medical_system.dto.RegisterRequest;
import com.healthcare.medical_system.entity.Medecin;
import com.healthcare.medical_system.entity.Patient;
import com.healthcare.medical_system.entity.Role;
import com.healthcare.medical_system.entity.User;
import com.healthcare.medical_system.repository.MedecinRepository;
import com.healthcare.medical_system.repository.PatientRepository;
import com.healthcare.medical_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final PatientRepository patientRepo;
    private final MedecinRepository medecinRepo;

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
         user = userRepo.save(user);

        if (request.getRole() == Role.PATIENT) {
            Patient patient = new Patient();
            patient.setNom(request.getNom());
            patient.setPrenom(request.getPrenom());
            patient.setEmail(request.getEmail());
            patient.setTelephone(request.getTelephone());
            patient.setDateNaissance(request.getDateNaissance());
            patient.setUser(user);
            patientRepo.save(patient);

        } else if(request.getRole() == Role.MEDECIN) {
            Medecin medecin = new Medecin();
            medecin.setNom(request.getNom());
            medecin.setTelephone(request.getTelephone());
            medecin.setEmail(request.getEmail());
            medecin.setSpecialite(request.getSpecialite());
            medecin.setUser(user);
            medecinRepo.save(medecin);

        }
            String token = jwtUtils.genererToken(user.getUsername(), user.getRole());

            return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        User user = userRepo.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        Role roleObj = user.getRole();
        String token = jwtUtils.genererToken(user.getUsername(), roleObj);

        return new AuthResponse(token);

    }
}
