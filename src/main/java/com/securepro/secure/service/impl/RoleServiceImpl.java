package com.securepro.secure.service.impl;

import com.securepro.secure.model.dto.request.RoleRequestDTO;
import com.securepro.secure.model.dto.response.RoleResponseDTO;
import com.securepro.secure.model.entity.Role;
import com.securepro.secure.model.mapper.RoleMapper;
import com.securepro.secure.repository.RoleRepository;
import com.securepro.secure.service.interfaces.RoleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public List<RoleResponseDTO> getAllRoles() {
        return roleRepository.findAll().stream()
                .map(roleMapper::toResponse)
                .toList();
    }

    @Override
    public RoleResponseDTO getRoleById(Integer id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role not found with ID: " + id));
        return roleMapper.toResponse(role);
    }

    @Override
    public RoleResponseDTO createRole(RoleRequestDTO roleRequestDTO) {
        if (roleRepository.findByName(roleRequestDTO.name()).isPresent()) {
            throw new IllegalArgumentException("Role already exists with name: " + roleRequestDTO.name());
        }
        Role role = roleMapper.toEntity(roleRequestDTO);
        role = roleRepository.save(role);
        return roleMapper.toResponse(role);
    }

    @Override
    public RoleResponseDTO updateRole(Integer id, RoleRequestDTO roleRequestDTO) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role not found with ID: " + id));
        role.setName(roleRequestDTO.name());
        role = roleRepository.save(role);
        return roleMapper.toResponse(role);
    }

    @Override
    public void deleteRole(Integer id) {
        if (!roleRepository.existsById(id)) {
            throw new EntityNotFoundException("Role not found with ID: " + id);
        }
        roleRepository.deleteById(id);
    }
}