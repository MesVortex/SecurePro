package com.securepro.secure.service.interfaces;

import com.securepro.secure.model.dto.request.UserRequestDTO;
import com.securepro.secure.model.dto.response.UserResponseDTO;

import java.util.List;

public interface UserService {

    /**
     * Registers a new user.
     *
     * @param userRequestDTO The user details for registration.
     * @return The registered user's details.
     */
    UserResponseDTO register(UserRequestDTO userRequestDTO);

    /**
     * Retrieves all users.
     *
     * @return A list of all users as UserResponseDTO.
     */
    List<UserResponseDTO> getAllUsers();

    /**
     * Updates the roles of a user.
     *
     * @param userId The ID of the user whose roles are to be updated.
     * @param roles The list of role names to assign to the user.
     */
    void updateUserRoles(Long userId, List<String> roles);
}