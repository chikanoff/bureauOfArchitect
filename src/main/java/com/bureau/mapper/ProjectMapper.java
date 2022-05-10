package com.bureau.mapper;

import com.bureau.model.dto.request.entities.ProjectDto;
import com.bureau.model.dto.response.ProjectResponse;
import com.bureau.model.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "city", ignore = true)
    @Mapping(target = "client", ignore = true)
    @Mapping(target = "type", ignore = true)
    @Mapping(target = "users", ignore = true)
    @Mapping(target = "date", ignore = true)
    @Mapping(target = "active", ignore = true)
    Project projectDtoToProject(ProjectDto projectDto);

    ProjectResponse projectToProjectResponse(Project project);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "city", ignore = true)
    @Mapping(target = "client", ignore = true)
    @Mapping(target = "type", ignore = true)
    @Mapping(target = "users", ignore = true)
    @Mapping(target = "date", ignore = true)
    @Mapping(target = "active", ignore = true)
    void updateProjectWithProjectDto(ProjectDto projectDto, @MappingTarget Project project);

    List<ProjectResponse> projectListToProjectResponseList(List<Project> projectList);
}
