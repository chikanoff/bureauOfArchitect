package com.bureau.model.dto.response;

import com.bureau.model.entity.UserRole;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserInfoResponse {
    private Long id;
    private String fullName;

    private String username;

    private String email;

    private UserRoleResponse role;
}
