package com.securepro.secure.controller;

import com.securepro.secure.model.dto.request.UserRequestDTO;
import com.securepro.secure.model.dto.response.UserResponseDTO;
import com.securepro.secure.service.interfaces.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/auth/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        UserResponseDTO createdUser = userService.register(userRequestDTO);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/admin/users")
    public ResponseEntity<List<UserResponseDTO>> listUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/admin/users/{id}/roles")
    public ResponseEntity<Void> updateRoles(@PathVariable Long id,@Valid @RequestBody List<String> roles) {
        userService.updateUserRoles(id, roles);
        return ResponseEntity.noContent().build();
    }
}