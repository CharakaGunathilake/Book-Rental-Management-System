package com.Newnop.Book_Rental_Management_System.service.impl;

import com.Newnop.Book_Rental_Management_System.dto.request.UserRequestDto;
import com.Newnop.Book_Rental_Management_System.dto.response.UserResponseDto;
import com.Newnop.Book_Rental_Management_System.repository.UserRepository;
import com.Newnop.Book_Rental_Management_System.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    @Override
    public void addUser(UserRequestDto dto) {

    }

    @Override
    public void updateUser(UserRequestDto dto) {

    }

    @Override
    public void deleteUser(Long userId) {

    }

    @Override
    public UserResponseDto getUserById(Long userId) {
        return null;
    }

    @Override
    public UserResponseDto getUserByUserName(String username) {
        return null;
    }

    @Override
    public UserResponseDto getUserByFirstAndLastName(String firstname, String lastname) {
        return null;
    }

    @Override
    public UserResponseDto getUserByEmail(String email) {
        return null;
    }

    @Override
    public UserResponseDto getUserByPhoneNumber(String phoneNumber) {
        return null;
    }

    @Override
    public List<UserResponseDto> getUsersByStatus(String status) {
        return List.of();
    }

    @Override
    public List<UserResponseDto> getUsersByFirstName(String firstName) {
        return List.of();
    }

    @Override
    public List<UserResponseDto> getUsersByLastName(String lastName) {
        return List.of();
    }

    @Override
    public List<UserResponseDto> getUsersByRole(String Role) {
        return List.of();
    }
}
