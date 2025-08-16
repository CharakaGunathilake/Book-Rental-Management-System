package com.Newnop.Book_Rental_Management_System.service.impl;

import com.Newnop.Book_Rental_Management_System.dto.request.AddressRequestDto;
import com.Newnop.Book_Rental_Management_System.dto.response.AddressResponseDto;
import com.Newnop.Book_Rental_Management_System.entity.Address;
import com.Newnop.Book_Rental_Management_System.repository.AddressRepository;
import com.Newnop.Book_Rental_Management_System.service.AddressService;
import com.Newnop.Book_Rental_Management_System.utils.common_utils.ValidationUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final ObjectMapper objectMapper;
    private final AddressRepository addressRepository;

    @Override
    public void addAddress(AddressRequestDto addressRequestDto) {
        try {
            // Convert DTO to entity
            Address address = objectMapper.convertValue(addressRequestDto, Address.class);

            // Save entity
            final Address savedEntity = addressRepository.save(address);

            log.info("Address added successfully by Id: {}", savedEntity.getId());
        } catch (Exception e) {
            log.error("Error while adding address: {}", e.getMessage(), e);
            throw new RuntimeException("Error while adding address", e);
        }
    }

    @Override
    public void updateAddress(Long id, AddressRequestDto addressRequestDto) {
        try {
            // Validate the id
            ValidationUtils.validateId("Address", id);

            // Convert DTO to entity
            Address address = objectMapper.convertValue(addressRequestDto, Address.class);

            // Save entity
            final Address updatedEntity = addressRepository.save(address);

            log.info("Address updated successfully by Id: {}", updatedEntity.getId());
        } catch (Exception e) {
            log.error("Error while updating address: {}", e.getMessage(), e);
            throw new RuntimeException("Error while updating address", e);
        }
    }

    @Override
    public void deleteAddress(Long addressId) {
        try {
            // Validate the addressId
            ValidationUtils.validateId("Address", addressId);
            // Check if the address exists
            if (!addressRepository.existsById(addressId)) {
                log.error("Address with ID {} does not exist", addressId);
                throw new RuntimeException("Address not found");
            }
            // Delete the address by ID
            addressRepository.deleteById(addressId);
            log.info("Address deleted successfully by Id: {}", addressId);
        } catch (Exception e) {
            log.error("Error while deleting address with ID {}: {}", addressId, e.getMessage(), e);
            throw new RuntimeException("Error while deleting address", e);
        }
    }

    @Override
    public AddressResponseDto getAddressById(Long addressId) {
        try {
            // Validate the addressId
            ValidationUtils.validateId("Address", addressId);
            // Find existing address
            Address address = addressRepository.findById(addressId)
                    .orElseThrow(() -> new RuntimeException("Address not found with ID: " + addressId));
            // Map to response DTO
            return objectMapper.convertValue(address, AddressResponseDto.class);
        } catch (Exception e) {
            log.error("Error while retrieving address with ID {}: {}", addressId, e.getMessage(), e);
            throw new RuntimeException("Error while retrieving address", e);
        }
    }

    @Override
    public List<AddressResponseDto> getAllAddresses() {
            // Fetch all addresses
            List<Address> addresses = addressRepository.findAll();

            // Check if the addresses is empty
            if (addresses.isEmpty()){
                log.info("No addresses found");
                return List.of();
            }

            // Map to response DTOs
            return addresses.stream()
                    .map(address -> objectMapper.convertValue(address, AddressResponseDto.class))
                    .toList();
    }
}
