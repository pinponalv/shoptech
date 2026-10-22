package com.companny.pinponalv.shoptech.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequest(
        @NotBlank(message = "La categoria no puede estar vacia")String name
        ) {}
