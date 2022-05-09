package com.bureau.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ClientResponse {
    private Long id;
    private String fullName;

    private String email;

    private String phone;

    private String organization;
}
