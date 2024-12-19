package com.securepro.secure.model.dto.response;

import lombok.Builder;

@Builder
public record ProductResponseDTO(
        Long id,
        String designation,
        double price,
        int quantity,
        Long userId,
        Long categoryId
) {}
