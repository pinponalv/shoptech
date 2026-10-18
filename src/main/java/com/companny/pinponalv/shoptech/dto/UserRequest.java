package com.companny.pinponalv.shoptech.dto;

import java.util.Set;

public record UserRequest(
        String name,
        String lastName,
        String phoneNumber,
        String email,
        String password,
        Set<RoleIdRequest> roles
) {}
