package com.companny.pinponalv.shoptech.service.impl;

import com.companny.pinponalv.shoptech.dto.PermissionRequest;
import com.companny.pinponalv.shoptech.dto.PermissionResponse;
import com.companny.pinponalv.shoptech.model.Permission;
import com.companny.pinponalv.shoptech.repository.PermissionRepository;
import com.companny.pinponalv.shoptech.service.IPermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionService implements IPermissionService {
    private final PermissionRepository permissionRepository;

    @Override
    public PermissionResponse createPermission(PermissionRequest request) {
        Permission newPermission = new Permission();
        newPermission.setPermissionName(request.getPermission());

        Permission savedPermission = permissionRepository.save(newPermission);
        return new PermissionResponse(
                savedPermission.getId(),
                savedPermission.getPermissionName()
        );
    }

    //TODO: CAMBIAR LA EXCEPCION POR UNA PERSONALIZADA
    @Override
    public PermissionResponse updatePermission(Long id,PermissionRequest request) {
        Permission permission = permissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Permission not found"));

        permission.setPermissionName(request.getPermission());
        Permission savedPermission = permissionRepository.save(permission);
        return new PermissionResponse(
                savedPermission.getId(),
                savedPermission.getPermissionName()
        );
    }

    //TODO: CAMBIAR LA EXCEPCION POR UNA PERSONALIZADA
    @Override
    public PermissionResponse findById(Long id) {
        Permission permission = permissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Permission not found"));
        return new PermissionResponse(
                permission.getId(),
                permission.getPermissionName()
        );
    }

    @Override
    public List<PermissionResponse> findAll() {
        List<Permission> permissions = permissionRepository.findAll();
        List<PermissionResponse> permissionDTO = new ArrayList<>();

        for(Permission permission : permissions){
            PermissionResponse permissionResponse = new PermissionResponse(
                    permission.getId(),
                    permission.getPermissionName()
            );
            permissionDTO.add(permissionResponse);
        }
        return permissionDTO;
    }

    //TODO: CAMBIAR ESTA EXCEPCION POR UNA PERSONALIZADA
    @Override
    public void deletePermission(Long id) {
        if(!permissionRepository.existsById(id)){
            throw new RuntimeException("Permission not found");
        }
        permissionRepository.deleteById(id);
    }
}
