package com.healthcare.medical_system.service;

import com.healthcare.medical_system.dto.UserDTO;
import com.healthcare.medical_system.entity.Role;
import com.healthcare.medical_system.entity.User;
import com.healthcare.medical_system.mapper.UserMapper;
import com.healthcare.medical_system.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    public Page<UserDTO> listerUsers(int page, int size, String sortDir){
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromString(sortDir), "username");
        return userRepository.findAll(pageable).map(userMapper::toDTO);
    }

    @Transactional
    public UserDTO consulterUser(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("utilisateur non trouve"));
        return userMapper.toDTO(user);
    }

    @Transactional
    public UserDTO modifierRole(Long id, Role role){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("utilisateur non trouve"));
        user.setRole(role);
        User savedUser = userRepository.save(user);
        return userMapper.toDTO(savedUser);
    }

    @Transactional
    public void supprimerUser(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("utilisateur non trouve"));
        userRepository.delete(user);
    }
}
