package com.securepro.secure.model.dto.response;

import java.util.List;

public record UserResponseDTO(
        Long id,
        String username,
        boolean active,
        List<String> roles
) {}
