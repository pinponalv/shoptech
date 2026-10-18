package com.companny.pinponalv.shoptech.dto;

import java.time.LocalDateTime;
import java.util.Set;

public record UserResponse(
        Long id,
        String name,
        String lastName,
        String numberPhone,
        String email,
        LocalDateTime createdAt,
        Set<RoleResponse>roles
) {}
