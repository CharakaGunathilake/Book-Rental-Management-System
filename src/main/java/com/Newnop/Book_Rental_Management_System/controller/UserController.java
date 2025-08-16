package com.Newnop.Book_Rental_Management_System.controller;

import com.Newnop.Book_Rental_Management_System.dto.request.AddressRequestDto;
import com.Newnop.Book_Rental_Management_System.dto.request.UserRequestDto;
import com.Newnop.Book_Rental_Management_System.dto.response.UserResponseDto;
import com.Newnop.Book_Rental_Management_System.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {
    // This class handles user-related endpoints.
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> addUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        log.info("Add new User attempt");
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(userRequestDto));
    }

    @PutMapping
    public ResponseEntity<UserResponseDto> updateUser(@RequestParam Long id, @Valid @RequestBody UserRequestDto userRequestDto) {
        log.info("Update existing User by id:{} attempt", id);
        return ResponseEntity.ok(userService.updateUser(id, userRequestDto));
    }

    @PatchMapping("/address")
    public ResponseEntity<UserResponseDto> updateUserAddress(@RequestParam Long id, @Valid @RequestBody AddressRequestDto addressRequestDto) {
        log.info("Update User address attempt: id: {}", id);
        return ResponseEntity.ok(userService.updateUserAddress(id, addressRequestDto));
    }

    @PatchMapping("/status")
    public ResponseEntity<UserResponseDto> updateUserStatus(@RequestParam Long id, @RequestParam String status) {
        log.info("Update User status attempt: id: {}, status: {}", id, status);
        return ResponseEntity.ok(userService.updateUserStatus(id, status));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@RequestParam Long id) {
        log.info("Delete existing User attempt by id: {}", id);
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<UserResponseDto> getUserById(@RequestParam Long id) {
        log.info("Retrieve User by id attempt: {}", id);
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/by_email")
    public ResponseEntity<UserResponseDto> getUserByEmail(@RequestParam String email) {
        log.info("Retrieve User by email attempt: {}", email);
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @GetMapping("/by_status")
    public ResponseEntity<List<UserResponseDto>> getUsersByStatus(@RequestParam String status) {
        log.info("Retrieve all Users by status attempt: {}", status);
        return ResponseEntity.ok(userService.getUsersByStatus(status));
    }

    @GetMapping("/by_role")
    public ResponseEntity<List<UserResponseDto>> getUsersByRole(@RequestParam String role) {
        log.info("Retrieve all Users by role attempt: {}", role);
        return ResponseEntity.ok(userService.getUsersByRole(role));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        log.info("Retrieve all User attempt");
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
