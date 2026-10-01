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
public class UserRequest {
    private String name;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
    private Set<RoleResponse> roles;
}
