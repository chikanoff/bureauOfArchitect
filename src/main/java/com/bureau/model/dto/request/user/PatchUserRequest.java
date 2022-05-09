package com.bureau.model.dto.request.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class PatchUserRequest {
    @NotEmpty
    @Size(min = 8, max = 40)
    private String password;
}
