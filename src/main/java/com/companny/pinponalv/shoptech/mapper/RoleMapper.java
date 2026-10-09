package com.companny.pinponalv.shoptech.mapper;

import com.companny.pinponalv.shoptech.dto.RoleResponse;
import com.companny.pinponalv.shoptech.model.Roles;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = PermissionMapper.class)
public interface RoleMapper {
    RoleResponse toResponse(Roles role);
}
