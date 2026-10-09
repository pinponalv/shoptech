package com.companny.pinponalv.shoptech.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResponse {
    private Long id;
    private String name;
    private String lastName;
    private String numberPhone;
    private String email;
    private String password;
    private LocalDateTime createdAt;
    private Set<RoleResponse> roles;
}
