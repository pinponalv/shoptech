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
public class RoleResponse {
    private Long id;
    private String role;
    private Set<PermissionResponse> permissions;
}
