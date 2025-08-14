package com.Newnop.Book_Rental_Management_System.service;

import com.Newnop.Book_Rental_Management_System.dto.request.UserRequestDto;
import com.Newnop.Book_Rental_Management_System.dto.response.UserResponseDto;

import java.util.List;

public interface UserService {
    void addUser(UserRequestDto dto);

    void updateUser(UserRequestDto dto);

    void deleteUser(Long userId);

    UserResponseDto getUserById(Long userId);

    UserResponseDto getUserByUserName(String username);

    UserResponseDto getUserByFirstAndLastName(String firstname, String lastname);

    UserResponseDto getUserByEmail(String email);

    UserResponseDto getUserByPhoneNumber(String phoneNumber);

    List<UserResponseDto> getUsersByStatus(String status);

    List<UserResponseDto> getUsersByFirstName(String firstName);

    List<UserResponseDto> getUsersByLastName(String lastName);

    List<UserResponseDto> getUsersByRole(String Role);
}
