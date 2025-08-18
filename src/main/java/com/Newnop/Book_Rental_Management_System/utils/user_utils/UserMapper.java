package com.Newnop.Book_Rental_Management_System.utils.user_utils;

import com.Newnop.Book_Rental_Management_System.dto.request.UserRequestDto;
import com.Newnop.Book_Rental_Management_System.dto.response.UserResponseDto;
import com.Newnop.Book_Rental_Management_System.entity.User;
import com.Newnop.Book_Rental_Management_System.enums.UserRole;
import com.Newnop.Book_Rental_Management_System.utils.address_utils.AddressMapper;

public class UserMapper {
    private UserMapper(){
        // Private constructor to prevent instantiation
    }

    public static User toEntity(UserRequestDto userRequestDto){
        return User.builder()
                .firstname(userRequestDto.getFirstname())
                .lastname(userRequestDto.getLastname())
                .email(userRequestDto.getEmail())
                .password(userRequestDto.getPassword())
                .phoneNumber(userRequestDto.getPhoneNumber())
                .address(AddressMapper.toEntity(userRequestDto.getAddress()))
                .userRole(UserRole.valueOf(userRequestDto.getUserRole()))
                .build();
    }

    public static UserResponseDto toDto(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .address(AddressMapper.toResponseDto(user.getAddress()))
                .userRole(user.getUserRole())
                .userStatus(user.getUserStatus())
                .build();
    }

    public static void updateEntity(UserRequestDto userRequestDto, User user) {
        user.setFirstname(userRequestDto.getFirstname());
        user.setLastname(userRequestDto.getLastname());
        user.setEmail(userRequestDto.getEmail());
        user.setPhoneNumber(userRequestDto.getPhoneNumber());
        user.setUserRole(UserRole.valueOf(userRequestDto.getUserRole()));
        // Note: Password should not be updated unless explicitly provided
    }
}
