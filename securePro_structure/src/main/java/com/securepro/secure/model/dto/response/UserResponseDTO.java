package com.securepro.secure.model.dto.response;

import java.util.List;

public record UserResponseDTO(
        Long id,
        String login,
        boolean active,
        List<String> roles
) {}
