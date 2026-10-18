package com.companny.pinponalv.shoptech.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.Set;
public record RoleRequest(
        @NotBlank
        String role,
        @NotEmpty
        Set<PermissionIdRequest> permissions
){}
