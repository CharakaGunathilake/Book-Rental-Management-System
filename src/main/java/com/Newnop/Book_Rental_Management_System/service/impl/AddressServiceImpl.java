package com.Newnop.Book_Rental_Management_System.service.impl;

import com.Newnop.Book_Rental_Management_System.dto.request.AddressRequestDto;
import com.Newnop.Book_Rental_Management_System.dto.response.AddressResponseDto;
import com.Newnop.Book_Rental_Management_System.repository.AddressRepository;
import com.Newnop.Book_Rental_Management_System.service.AddressService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final ObjectMapper objectMapper;
    private final AddressRepository addressRepository;

    @Override
    public void addAddress(AddressRequestDto addressRequestDto) {

    }

    @Override
    public void updateAddress(AddressRequestDto addressRequestDto) {

    }

    @Override
    public void deleteAddress(Long addressId) {

    }

    @Override
    public AddressResponseDto getAddressById(Long addressId) {
        return null;
    }

    @Override
    public AddressResponseDto getAddressByUserId(Long userId) {
        return null;
    }

    @Override
    public List<AddressResponseDto> getAllAddresses() {
        return List.of();
    }
}
