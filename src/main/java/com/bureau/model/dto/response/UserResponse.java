package com.bureau.model.dto.response;

import com.bureau.model.entity.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private Long id;

    private String fullName;

    private String username;

    private String email;

    private UserRoleResponse role;
}
