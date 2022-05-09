package com.bureau.mapper;

import com.bureau.model.dto.request.entities.ProjectTypeDto;
import com.bureau.model.dto.response.ProjectTypeResponse;
import com.bureau.model.entity.ProjectType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProjectTypeMapper {
    @Mapping(target = "id", ignore = true)
    ProjectType projectTypeDtoToProjectType(ProjectTypeDto projectTypeDto);
    ProjectTypeResponse projectTypeToProjectTypeResponse(ProjectType projectType);

    @Mapping(target = "id", ignore = true)
    void updateTypeWithTypeDto(ProjectTypeDto projectTypeDto, @MappingTarget ProjectType projectType);
}
