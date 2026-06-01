package com.healthcare.medical_system.controller;

import com.healthcare.medical_system.dto.UserDTO;
import com.healthcare.medical_system.entity.Role;
import com.healthcare.medical_system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<Page<UserDTO>> listerUsers(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "5") int size,
                                                     @RequestParam(defaultValue = "asc") String sortDir){
        return ResponseEntity.ok(userService.listerUsers(page, size, sortDir));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> consulterUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.consulterUser(id));
    }

    @PutMapping("/{id}/role")
    public ResponseEntity<UserDTO> modifierRole(@PathVariable Long id, @RequestParam Role role){
        return ResponseEntity.ok(userService.modifierRole(id, role));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerUser(@PathVariable Long id){
        userService.supprimerUser(id);
        return ResponseEntity.noContent().build();
    }
}
