package com.securepro.secure.service.impl;

import com.securepro.secure.model.dto.request.UserRequestDTO;
import com.securepro.secure.model.dto.response.UserResponseDTO;
import com.securepro.secure.model.entity.Role;
import com.securepro.secure.model.entity.User;
import com.securepro.secure.model.mapper.UserMapper;
import com.securepro.secure.repository.RoleRepository;
import com.securepro.secure.repository.UserRepository;
import com.securepro.secure.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDTO register(UserRequestDTO userRequestDTO) {
        User user = userMapper.toEntity(userRequestDTO);
        user.setPassword(passwordEncoder.encode(userRequestDTO.password()));
        user.setRoles(List.of(roleRepository.findByName("ROLE_USER").orElseThrow()));
        user.setActive(true);
        return userMapper.toResponse(userRepository.save(user));
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void updateUserRoles(Long userId, List<String> roles) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        List<Role> updatedRoles = roles.stream()
                .map(roleName -> roleRepository.findByName(roleName).orElseThrow())
                .collect(Collectors.toList());
        user.setRoles(updatedRoles);
        userRepository.save(user);
    }
}