package com.Newnop.Book_Rental_Management_System.service.impl;

import com.Newnop.Book_Rental_Management_System.dto.request.AddressRequestDto;
import com.Newnop.Book_Rental_Management_System.dto.request.UserRequestDto;
import com.Newnop.Book_Rental_Management_System.dto.response.UserResponseDto;
import com.Newnop.Book_Rental_Management_System.repository.UserRepository;
import com.Newnop.Book_Rental_Management_System.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;


    @Override
    public UserResponseDto addUser(UserRequestDto dto) {
        return null;
    }

    @Override
    public UserResponseDto updateUser(UserRequestDto dto) {
        return null;
    }

    @Override
    public UserResponseDto updateUserAddress(Long userId, AddressRequestDto address) {
        return null;
    }

    @Override
    public UserResponseDto updateUserStatus(Long userId, String status) {
        return null;
    }

    @Override
    public void deleteUser(Long userId) {
    }

    @Override
    public UserResponseDto getUserById(Long userId) {
        return null;
    }

    @Override
    public UserResponseDto getUserByEmail(String email) {
        return null;
    }

    @Override
    public List<UserResponseDto> getUsersByStatus(String status) {
        return List.of();
    }

    @Override
    public List<UserResponseDto> getUsersByRole(String Role) {
        return List.of();
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return List.of();
    }
}
