package com.Newnop.Book_Rental_Management_System.service;

import com.Newnop.Book_Rental_Management_System.dto.request.AddressRequestDto;
import com.Newnop.Book_Rental_Management_System.dto.response.AddressResponseDto;

import java.util.List;

public interface AddressService {
    void addAddress(AddressRequestDto addressRequestDto);

    void updateAddress(Long id, AddressRequestDto addressRequestDto);

    void deleteAddress(Long addressId);

    AddressResponseDto getAddressById(Long addressId);

    List<AddressResponseDto> getAllAddresses();
}
