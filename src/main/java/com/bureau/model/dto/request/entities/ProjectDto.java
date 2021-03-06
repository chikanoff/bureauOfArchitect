package com.bureau.model.dto.request.entities;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ProjectDto {
    @NotEmpty
    private String name;

    @NotNull
    private Long typeId;

    @NotNull
    private Long cityId;

    @NotNull
    private Long clientId;

    @NotEmpty
    private String notes;

    @NotEmpty
    private String address;

    @NotNull
    private String projectUrl;

}
