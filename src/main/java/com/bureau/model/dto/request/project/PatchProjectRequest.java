package com.bureau.model.dto.request.project;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PatchProjectRequest {
    @NotNull
    private boolean active;
}
