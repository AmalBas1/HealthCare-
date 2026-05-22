package com.healthcare.medical_system.config;


import com.healthcare.medical_system.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception{
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth ->  auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/users/**").hasRole("ADMIN")
                        .requestMatchers("/api/medecins/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/patients/*").hasAnyRole("ADMIN", "PATIENT")
                        .requestMatchers(HttpMethod.PUT, "/api/patients/*").hasAnyRole("ADMIN", "PATIENT")
                        .requestMatchers("/api/patients/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/dossier-medicaux/*/diagnostic").hasRole("MEDECIN")
                        .requestMatchers(HttpMethod.PUT, "/api/dossier-medicaux/*/observations").hasRole("MEDECIN")
                        .requestMatchers(HttpMethod.GET, "/api/dossier-medicaux/**").hasAnyRole("ADMIN", "MEDECIN")
                        .requestMatchers(HttpMethod.POST, "/api/dossier-medicaux/**").hasAnyRole("ADMIN", "MEDECIN")
                        .requestMatchers(HttpMethod.PUT, "/api/dossier-medicaux/**").hasAnyRole("ADMIN", "MEDECIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/dossier-medicaux/**").hasRole("ADMIN")
                        .requestMatchers("/api/rendez-vous/**").hasAnyRole("ADMIN", "MEDECIN", "PATIENT")
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }
}
