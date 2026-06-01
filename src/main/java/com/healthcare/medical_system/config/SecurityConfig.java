package com.healthcare.medical_system.config;


import com.healthcare.medical_system.filter.JwtAuthenticationFilter;
import com.healthcare.medical_system.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

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
                        .requestMatchers(HttpMethod.GET, "/api/patients").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/patients/search").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/patients/*").hasAnyRole("ADMIN", "PATIENT")
                        .requestMatchers(HttpMethod.PUT, "/api/patients/*").hasAnyRole("ADMIN", "PATIENT")
                        .requestMatchers("/api/patients/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/dossiers-medicaux").hasAnyRole("ADMIN", "MEDECIN")
                        .requestMatchers(HttpMethod.GET, "/api/dossiers-medicaux/*").hasAnyRole("ADMIN", "MEDECIN", "PATIENT")
                        .requestMatchers(HttpMethod.POST, "/api/dossiers-medicaux/*/diagnostic").hasAnyRole("ADMIN", "MEDECIN")
                        .requestMatchers(HttpMethod.POST, "/api/dossiers-medicaux/*/observation").hasAnyRole("ADMIN", "MEDECIN")
                        .requestMatchers(HttpMethod.POST, "/api/dossiers-medicaux").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/dossiers-medicaux/**").hasAnyRole("ADMIN", "MEDECIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/dossiers-medicaux/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/appointments").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/appointments/search").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/appointments/patient/*").hasAnyRole("ADMIN", "PATIENT")
                        .requestMatchers(HttpMethod.GET, "/api/appointments/medecin/*").hasAnyRole("ADMIN", "MEDECIN")
                        .requestMatchers("/api/appointments/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
