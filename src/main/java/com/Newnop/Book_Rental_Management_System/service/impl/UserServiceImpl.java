package com.Newnop.Book_Rental_Management_System.service.impl;

import com.Newnop.Book_Rental_Management_System.dto.request.AddressRequestDto;
import com.Newnop.Book_Rental_Management_System.dto.request.UserRequestDto;
import com.Newnop.Book_Rental_Management_System.dto.response.UserResponseDto;
import com.Newnop.Book_Rental_Management_System.entity.User;
import com.Newnop.Book_Rental_Management_System.enums.UserRole;
import com.Newnop.Book_Rental_Management_System.enums.UserStatus;
import com.Newnop.Book_Rental_Management_System.exception.custom.UserServiceException;
import com.Newnop.Book_Rental_Management_System.repository.UserRepository;
import com.Newnop.Book_Rental_Management_System.service.UserService;
import com.Newnop.Book_Rental_Management_System.utils.address_utils.AddressMapper;
import com.Newnop.Book_Rental_Management_System.utils.common_utils.ValidationUtils;
import com.Newnop.Book_Rental_Management_System.utils.user_utils.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public UserResponseDto addUser(UserRequestDto dto) {
        //Convert UserRequestDto to User entity
        User user = UserMapper.toEntity(dto);
        User savedUser;
        try {
            //Save User entity to the database
            savedUser = userRepository.save(user);
            log.info("User added successfully with ID: {}", savedUser.getId());
        } catch (DataAccessException e) {
            //Handle database-specific errors (e.g., constraint violations, duplicates)
            log.error("Error while adding user: {}", e.getMessage(), e);
            throw new UserServiceException("Error while adding user", e);
        }
        //Convert saved User entity to UserResponseDto
        return UserMapper.toDto(savedUser);
    }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto dto) {
        //Validate the user ID
        ValidationUtils.validateId("User", id);

        //Find existing user by ID
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));

        //Update fields in the existing user entity
        UserMapper.updateEntity(dto, existingUser);

        User updatedUser;
        try {
            //Save the updated user entity to the database
            updatedUser = userRepository.save(existingUser);
            log.info("User updated successfully with ID: {}", updatedUser.getId());
        } catch (DataAccessException e) {
            log.error("Error while updating user: {}", e.getMessage(), e);
            throw new UserServiceException("Error while updating user", e);
        }
        //Convert updated User entity to UserResponseDto
        return UserMapper.toDto(updatedUser);
    }

    @Override
    public UserResponseDto updateUserAddress(Long userId, AddressRequestDto address) {
        // Validate the user ID
        ValidationUtils.validateId("User", userId);

        // Find existing user by ID
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

        // Update address in the existing user entity
        AddressMapper.updateEntity(address, existingUser.getAddress());

        User updatedUser;
        try {
            // Save the updated user entity to the database
            updatedUser = userRepository.save(existingUser);
            log.info("User address updated successfully with ID: {}", updatedUser.getId());
        } catch (DataAccessException e) {
            log.error("Error while updating user address: {}", e.getMessage(), e);
            throw new UserServiceException("Error while updating user address", e);
        }
        // Convert updated User entity to UserResponseDto
        return UserMapper.toDto(updatedUser);
    }

    @Override
    public UserResponseDto updateUserStatus(Long userId, String status) {
        // Validate the user ID
        ValidationUtils.validateId("User", userId);

        // Find existing user by ID
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

        // Convert status to enum
        try {
            // Trim and convert to Uppercase to match enum values
            UserStatus finalStatus =  UserStatus.valueOf(status.trim().toUpperCase());
            // Update status in the existing user entity
            existingUser.setUserStatus(finalStatus);
        } catch (IllegalArgumentException e) {
            log.error("Invalid user status: {}", e.getMessage(), e);
            throw new UserServiceException("Invalid user status", e);
        }

        User updatedUser;
        try {
            // Save the updated user entity to the database
            updatedUser = userRepository.save(existingUser);
            log.info("User status updated successfully with ID: {}", updatedUser.getId());
        } catch (DataAccessException e) {
            log.error("Error while updating user status: {}", e.getMessage(), e);
            throw new UserServiceException("Error while updating user status", e);
        }
        // Convert updated User entity to UserResponseDto
        return UserMapper.toDto(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        // Validate the user ID
        ValidationUtils.validateId("User", userId);

        // Check if user exists
        if (!userRepository.existsById(userId)) {
            log.error("User with ID {} not found for deletion", userId);
            throw new EntityNotFoundException("User not found with ID: " + userId);
        }

        try {
            // Delete the user by ID
            userRepository.deleteById(userId);
            log.info("User with ID {} deleted successfully", userId);
        } catch (DataAccessException e) {
            log.error("Error while deleting user: {}", e.getMessage(), e);
            throw new UserServiceException("Error while deleting user", e);
        }
    }

    @Override
    public UserResponseDto getUserById(Long userId) {
        // Validate the user ID
        ValidationUtils.validateId("User", userId);

        // Find existing user by ID
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

        log.info("User retrieved successfully with ID: {}", user.getId());
        // Convert User entity to UserResponseDto
        return UserMapper.toDto(user);
    }

    @Override
    public UserResponseDto getUserByEmail(String email) {
        // Validate the email
        ValidationUtils.validateEmail("User", email);

        // Find existing user by email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));

        log.info("User retrieved successfully with email: {}", email);
        // Convert User entity to UserResponseDto
        return UserMapper.toDto(user);
    }

    @Override
    public List<UserResponseDto> getUsersByStatus(String status) {
        // Validate the status
        if (status == null || status.isBlank()) {
            log.error("Status cannot be null or empty");
            throw new IllegalArgumentException("Status cannot be null or empty");
        }

        // Convert status to enum
        UserStatus userStatus;
        try {
            // Trim and convert to Uppercase to match enum values
            userStatus = UserStatus.valueOf(status.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            log.error("Invalid user status: {}", e.getMessage(), e);
            throw new UserServiceException("Invalid user status", e);
        }

        // Find users by status
        List<User> users = userRepository.findByUserStatus(userStatus);
        if (users.isEmpty()) {
            log.warn("No users found with status: {}", userStatus);
            return List.of();
        }

        log.info("Retrieved {} users with status: {}", users.size(), userStatus);
        // Convert User entities to UserResponseDto
        return users.stream()
                .map(UserMapper::toDto)
                .toList();
    }

    @Override
    public List<UserResponseDto> getUsersByRole(String Role) {
        // Validate the role
        if (Role == null || Role.isBlank()) {
            log.error("Role cannot be null or empty");
            throw new IllegalArgumentException("Role cannot be null or empty");
        }

        // Convert role to enum (assuming Role is an enum, adjust if necessary)
        UserRole roleEnum;
        try {
            // Trim and convert to Uppercase to match enum values
             roleEnum = UserRole.valueOf(Role.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            log.error("Invalid user role: {}", e.getMessage(), e);
            throw new UserServiceException("Invalid user role", e);
        }

        // Find users by role (assuming User entity has a method to get role)
        List<User> users = userRepository.findAllByUserRole(roleEnum);

        if (users.isEmpty()) {
            log.warn("No users found with role: {}", Role);
            return List.of();
        }

        log.info("Retrieved {} users with role: {}", users.size(), Role);
        // Convert User entities to UserResponseDto
        return users.stream()
                .map(UserMapper::toDto)
                .toList();
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        // Retrieve all users from the repository
        List<User> users = userRepository.findAll();

        // Check if users list is empty
        if (users.isEmpty()) {
            log.warn("No users found");
            return List.of(); // Return empty list if no users found
        }

        log.info("Retrieved {} users successfully", users.size());
        // Convert User entities to UserResponseDto
        return users.stream()
                .map(UserMapper::toDto)
                .toList();
    }
}
