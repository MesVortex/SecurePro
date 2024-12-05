package com.securepro.secure.model.dto.request;

public record ProductRequestDTO(
        String designation,
        double price,
        int quantity,
        Long userId,
        Long categoryId
) {}
