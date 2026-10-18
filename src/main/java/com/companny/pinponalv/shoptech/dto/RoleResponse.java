package com.companny.pinponalv.shoptech.dto;

import java.util.Set;

public record RoleResponse(
        Long id,
        String role,
        Set<PermissionResponse> permissions
) {}
