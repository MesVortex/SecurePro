package com.securepro.secure.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RoleRequestDTO(
        @NotBlank(message = "Role name is required")
        @Size(min = 3, max = 30, message = "Role name must be between 3 and 30 characters")
        String name
) {}