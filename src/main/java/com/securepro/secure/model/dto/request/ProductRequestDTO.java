package com.securepro.secure.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRequestDTO(
        @NotBlank(message = "Designation is required")
        String designation,

        @Min(value = 0, message = "Price must be at least 0")
        double price,

        @Min(value = 0, message = "Quantity must be at least 0")
        int quantity,

        @NotNull(message = "User ID is required")
        Long userId,

        @NotNull(message = "Category ID is required")
        Long categoryId
) {}
