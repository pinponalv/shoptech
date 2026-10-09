package com.companny.pinponalv.shoptech.mapper;

import com.companny.pinponalv.shoptech.dto.PermissionResponse;
import com.companny.pinponalv.shoptech.model.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    PermissionResponse toResponse(Permission permission);
}
