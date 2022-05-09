package com.bureau.mapper;

import com.bureau.model.dto.request.user.CreateUserRequest;
import com.bureau.model.dto.request.user.UpdateUserRequest;
import com.bureau.model.dto.response.UserInfoResponse;
import com.bureau.model.dto.response.UserResponse;
import com.bureau.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "projects", ignore = true)
    User createUserRequestToUser(CreateUserRequest req);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "projects", ignore = true)
    void updateUserWithUpdateUserRequest(UpdateUserRequest req, @MappingTarget User user);

    UserInfoResponse userToUserInfoResponse(User user);

    UserResponse userToUserResponse(User user);
}
