package com.bureau.model.dto.request.entities;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ProjectTypeDto {
    @NotEmpty
    private String name;
}
