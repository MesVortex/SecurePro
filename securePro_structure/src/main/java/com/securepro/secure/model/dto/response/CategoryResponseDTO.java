package com.securepro.secure.model.dto.response;

import lombok.Builder;

@Builder
public record CategoryResponseDTO(
        Long id,
        String name,
        String description
) {}
