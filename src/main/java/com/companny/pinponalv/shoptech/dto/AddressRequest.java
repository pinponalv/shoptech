package com.companny.pinponalv.shoptech.dto;

import jakarta.validation.constraints.NotBlank;

public record AddressRequest (
    @NotBlank(message = "cannot be empty")
    String street,
    @NotBlank(message = "cannot be empty") String city,
    @NotBlank(message = "cannot be empty") String state,
    @NotBlank(message = "cannot be empty") String zip,
    @NotBlank(message = "cannot be empty") String number,
    @NotBlank(message = "cannot be empty") String description
){}
