package com.bureau.mapper;

import com.bureau.model.dto.response.UserInfoResponse;
import com.bureau.model.dto.response.UserRoleResponse;
import com.bureau.model.entity.User;
import com.bureau.model.entity.UserRole;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    UserRoleResponse roleToRoleResponse(UserRole role);
}
