package com.vsv.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SoftwareEngineerRequest(
        @NotBlank(message = "Name is required") String name,
        @NotBlank(message = "Tech stack is required") String techStack,
        @NotBlank(message = "Email is required") @Email(message = "Must be a valid email") String email
) {}
