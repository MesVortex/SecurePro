package com.securepro.secure.model.dto.request;

import lombok.Builder;

@Builder
public record CategoryRequestDTO(
        String name,
        String description
){}
