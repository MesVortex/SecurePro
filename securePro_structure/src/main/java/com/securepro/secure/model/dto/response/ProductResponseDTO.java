package com.securepro.secure.model.dto.response;

public record ProductResponseDTO(
        Long id,
        String designation,
        double price,
        int quantity,
        Long userId,
        Long categoryId
) {}
