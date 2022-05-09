package com.bureau.model.dto.response;

import com.bureau.model.entity.ProjectType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ProjectResponse {
    private Long id;
    private String name;

    private ProjectType type;

    private CityResponse city;

    private ClientResponse client;

    private Date date;

    private String notes;

    private String address;

    private boolean isActive;

    private String projectUrl;

    private Set<UserInfoResponse> users = new HashSet<>();
}
