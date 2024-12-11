package com.securepro.secure.service.interfaces;

import com.securepro.secure.model.dto.request.RoleRequestDTO;
import com.securepro.secure.model.dto.response.RoleResponseDTO;

import java.util.List;

public interface RoleService {
    List<RoleResponseDTO> getAllRoles();

    RoleResponseDTO getRoleById(Integer id);

    RoleResponseDTO createRole(RoleRequestDTO roleRequestDTO);

    RoleResponseDTO updateRole(Integer id, RoleRequestDTO roleRequestDTO);

    void deleteRole(Integer id);
}