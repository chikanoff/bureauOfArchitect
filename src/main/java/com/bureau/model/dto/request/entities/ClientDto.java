package com.bureau.model.dto.request.entities;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ClientDto {
    @NotEmpty
    private String fullName;

    @NotEmpty
    private String email;

    @NotEmpty
    private String phone;

    @NotEmpty
    private String organization;
}
