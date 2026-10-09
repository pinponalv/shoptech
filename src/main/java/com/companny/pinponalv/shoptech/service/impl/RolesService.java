package com.companny.pinponalv.shoptech.service.impl;

import com.companny.pinponalv.shoptech.dto.PermissionIdRequest;
import com.companny.pinponalv.shoptech.dto.PermissionResponse;
import com.companny.pinponalv.shoptech.dto.RoleRequest;
import com.companny.pinponalv.shoptech.dto.RoleResponse;
import com.companny.pinponalv.shoptech.model.Permission;
import com.companny.pinponalv.shoptech.model.Roles;
import com.companny.pinponalv.shoptech.repository.PermissionRepository;
import com.companny.pinponalv.shoptech.repository.RolesRepository;
import com.companny.pinponalv.shoptech.service.IRolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RolesService implements IRolesService {
    private final RolesRepository rolesRepository;
    private final PermissionRepository permissionRepository;

    @Override
    public RoleResponse createRole(RoleRequest roleRequest) {
        Roles role = new Roles();

        role.setRole(roleRequest.getRole());
        role.setPermissionsList(resolvePermissions(roleRequest.getPermissions()));
        Roles saved = rolesRepository.save(role);

        return toRoleResponse(saved);
    }


    //TODO: CAMBIAR ESTA EXCEPCION
    @Override
    public RoleResponse updateRole(Long id,RoleRequest roleRequest) {
        Roles role = rolesRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));

        if(roleRequest.getRole() != null) {
            role.setRole(roleRequest.getRole());
        }

        if(roleRequest.getPermissions() != null) {
            role.setPermissionsList(resolvePermissions(roleRequest.getPermissions()));
        }

        Roles saved = rolesRepository.save(role);
        return toRoleResponse(saved);
    }

    @Override
    public List<RoleResponse> findAll() {
        List<Roles> roles = rolesRepository.findAll();
        List<RoleResponse> roleResponseList = new ArrayList<>();

        for (Roles role : roles) {
            roleResponseList.add(toRoleResponse(role));
        }
        return roleResponseList;
    }

    //TODO: cambiar esta excepcion
    @Override
    public RoleResponse findById(Long id) {
        Roles role = rolesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        return toRoleResponse(role);
    }
    //TODO: cambiar esta excepcion
    @Override
    public void deleteRole(Long id) {
        if(!rolesRepository.existsById(id)){
            throw new RuntimeException("Role not found");
        }
        rolesRepository.deleteById(id);
    }

    //TODO: CAMBIAR EXCEPCION AQUI
    private Set<Permission> resolvePermissions(Set<PermissionIdRequest> permissionId) {
        Set<Permission> permissions = new HashSet<>();
        for(PermissionIdRequest permission : permissionId){
            Permission readPermission = permissionRepository.findById(permission.getId())
                    .orElseThrow(() -> new RuntimeException("Permission not found"));
            permissions.add(readPermission);
        }
        return permissions;
    }

    private RoleResponse toRoleResponse(Roles roles) {
        Set<PermissionResponse> permissionList = new HashSet<>();
        for(Permission permission: roles.getPermissionsList()){
            PermissionResponse response = new PermissionResponse();

            response.setId(permission.getId());
            response.setPermission(permission.getPermissionName());
            permissionList.add(response);
        }

        RoleResponse roleResponse = new RoleResponse();
        roleResponse.setId(roles.getId());
        roleResponse.setRole(roles.getRole());
        roleResponse.setPermissions(permissionList);
        return roleResponse;
    }
}
