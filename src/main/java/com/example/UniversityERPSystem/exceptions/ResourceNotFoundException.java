package com.example.UniversityERPSystem.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    // Constructor receives the error message.
    public ResourceNotFoundException(String message) {
        super(message);
    }
}