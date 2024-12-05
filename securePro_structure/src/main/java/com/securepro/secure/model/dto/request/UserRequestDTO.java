package com.securepro.secure.model.dto.request;

import java.util.List;

public record UserRequestDTO(
        String login,
        String password,
        boolean active,
        List<Integer> roleIds
) {}
