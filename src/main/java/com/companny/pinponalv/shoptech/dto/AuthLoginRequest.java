package com.companny.pinponalv.shoptech.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthLoginRequest (
        @Email
        @NotBlank(message = "No puede estar vacio")
        String email,
        @NotBlank(message = "No puede estar vacio")
        String password
){}
