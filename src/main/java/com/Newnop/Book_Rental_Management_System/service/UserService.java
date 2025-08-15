package com.Newnop.Book_Rental_Management_System.service;

import com.Newnop.Book_Rental_Management_System.dto.request.AddressRequestDto;
import com.Newnop.Book_Rental_Management_System.dto.request.UserRequestDto;
import com.Newnop.Book_Rental_Management_System.dto.response.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto addUser(UserRequestDto dto);

    UserResponseDto updateUser(UserRequestDto dto);

    UserResponseDto updateUserAddress(Long userId, AddressRequestDto address);

    UserResponseDto updateUserStatus(Long userId, String status);

    void deleteUser(Long userId);

    UserResponseDto getUserById(Long userId);

    UserResponseDto getUserByEmail(String email);

    List<UserResponseDto> getUsersByStatus(String status);

    List<UserResponseDto> getUsersByRole(String Role);

    List<UserResponseDto> getAllUsers();
}
