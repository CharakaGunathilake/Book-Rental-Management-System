package com.Newnop.Book_Rental_Management_System.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/authors")
@RequiredArgsConstructor
public class AuthorController {
    // This class handles author-related endpoints.
    @GetMapping("/test")
    public String testEndpoint() {
        log.info("Test endpoint hit");
        return "Author Controller is working!";
    }
}
