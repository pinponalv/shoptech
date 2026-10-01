package com.companny.pinponalv.shoptech.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoleRequest {
    private String role;
    private Set<PermissionRequest> permissions;
}
