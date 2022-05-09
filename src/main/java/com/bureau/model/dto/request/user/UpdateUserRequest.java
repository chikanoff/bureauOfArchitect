package com.bureau.model.dto.request.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UpdateUserRequest {
    @NotEmpty
    @Size(min = 3, max = 128)
    private String fullName;

    @NotEmpty
    @Size(min = 5, max = 32)
    private String username;

    @NotEmpty
    @Size(max = 64)
    @Email(message = "email is not valid")
    private String email;

    private Long roleId;
}
