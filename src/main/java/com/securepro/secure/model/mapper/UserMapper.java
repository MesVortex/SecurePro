package com.securepro.secure.model.mapper;

import com.securepro.secure.model.dto.request.UserRequestDTO;
import com.securepro.secure.model.dto.response.UserResponseDTO;
import com.securepro.secure.model.entity.User;
import com.securepro.secure.model.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface UserMapper {
    @Mapping(target = "roles", ignore = true)
    User toEntity(UserRequestDTO dto);

    @Mapping(target = "roles", expression = "java(mapRolesToNames(user.getRoles()))")
    UserResponseDTO toResponse(User user);

    default List<String> mapRolesToNames(List<Role> roles) {
        return roles.stream().map(Role::getName).toList();
    }
}