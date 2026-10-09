package com.companny.pinponalv.shoptech.mapper;

import com.companny.pinponalv.shoptech.dto.UserResponse;
import com.companny.pinponalv.shoptech.model.UserSec;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = RoleMapper.class)
public interface UserMapper {
    @Mapping(target = "password", ignore = true)
    UserResponse toResponse(UserSec user);
}
