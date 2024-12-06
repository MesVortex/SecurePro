package com.securepro.secure.model.mapper;

import com.securepro.secure.model.dto.request.RoleRequestDTO;
import com.securepro.secure.model.dto.response.RoleResponseDTO;
import com.securepro.secure.model.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface RoleMapper {
    Role toEntity(RoleRequestDTO dto);

    RoleResponseDTO toResponse(Role entity);
}