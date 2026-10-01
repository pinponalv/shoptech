package com.companny.pinponalv.shoptech.service;

import com.companny.pinponalv.shoptech.dto.RoleRequest;
import com.companny.pinponalv.shoptech.dto.RoleResponse;

import java.util.List;

public interface IRolesService {
    RoleResponse createRole(RoleRequest roleRequest);
    RoleResponse updateRole(RoleRequest roleRequest);
    List<RoleResponse> findAll();
    RoleResponse findById(Long id);
    void deleteRole(Long id);
}
