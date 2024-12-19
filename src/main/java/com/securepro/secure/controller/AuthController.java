package com.securepro.secure.controller;

import com.securepro.secure.model.dto.request.LoginRequestDTO;
import com.securepro.secure.model.dto.request.UserRequestDTO;
import com.securepro.secure.model.dto.response.UserResponseDTO;
import com.securepro.secure.service.interfaces.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/auth/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.username(),
                        loginRequestDTO.password()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.ok("Login successful!");
    }

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
    public ResponseEntity<Void> updateRoles(@PathVariable Long id, @Valid @RequestBody List<String> roles) {
        userService.updateUserRoles(id, roles);
        return ResponseEntity.noContent().build();
    }
}