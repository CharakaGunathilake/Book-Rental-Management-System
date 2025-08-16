package com.Newnop.Book_Rental_Management_System.utils.address_utils;

import com.Newnop.Book_Rental_Management_System.dto.request.AddressRequestDto;
import com.Newnop.Book_Rental_Management_System.dto.response.AddressResponseDto;
import com.Newnop.Book_Rental_Management_System.entity.Address;

public class AddressMapper {
    private AddressMapper() {
        // Private constructor to prevent instantiation
    }

    // method to convert AddressRequestDto to Address entity
    public static Address toEntity(AddressRequestDto addressRequestDto) {
        return Address.builder()
                .addressLine1(addressRequestDto.getAddressLine1())
                .addressLine1(addressRequestDto.getAddressLine2())
                .addressLine3(addressRequestDto.getAddressLine3())
                .city(addressRequestDto.getCity())
                .postalCode(addressRequestDto.getPostalCode())
                .build();
    }

    // method to convert Address entity to AddressRequestDto
    public static AddressResponseDto toResponseDto(Address address) {
        return AddressResponseDto.builder()
                .id(address.getId())
                .addressLine1(address.getAddressLine1())
                .addressLine2(address.getAddressLine2())
                .addressLine3(address.getAddressLine3())
                .city(address.getCity())
                .postalCode(address.getPostalCode())
                .build();
    }

    // method to update Address entity from AddressRequestDto
    public static void updateEntity(AddressRequestDto addressRequestDto, Address address) {
        address.setAddressLine1(addressRequestDto.getAddressLine1());
        address.setAddressLine2(addressRequestDto.getAddressLine2());
        address.setAddressLine3(addressRequestDto.getAddressLine3());
        address.setCity(addressRequestDto.getCity());
        address.setPostalCode(addressRequestDto.getPostalCode());
    }
}
