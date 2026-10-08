package com.companny.pinponalv.shoptech.service;

import com.companny.pinponalv.shoptech.dto.PermissionRequest;
import com.companny.pinponalv.shoptech.dto.PermissionResponse;

import java.util.List;

public interface IPermissionService {
    PermissionResponse createPermission(PermissionRequest request);
    PermissionResponse updatePermission(Long id,PermissionRequest request);
    PermissionResponse findById(Long id);
    List<PermissionResponse> findAll();
    void deletePermission(Long id);
}
