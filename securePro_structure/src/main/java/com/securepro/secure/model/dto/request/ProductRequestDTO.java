package com.securepro.secure.model.dto.request;

import lombok.Builder;

@Builder
public record ProductRequestDTO(
        String designation,
        double price,
        int quantity,
        Long userId,
        Long categoryId
) {}
