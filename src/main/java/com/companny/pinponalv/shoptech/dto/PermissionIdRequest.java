package com.companny.pinponalv.shoptech.dto;

import jakarta.validation.constraints.NotNull;

public record
PermissionIdRequest (
        @NotNull(message = "el id es obligatorio")
        Long id
){}
