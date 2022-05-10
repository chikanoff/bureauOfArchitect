package com.bureau.service;

import com.bureau.exception.ItemNotFoundException;
import com.bureau.mapper.ProjectMapper;
import com.bureau.model.dto.request.entities.ProjectDto;
import com.bureau.model.dto.request.project.GetProjectsByDatesBetweenRequest;
import com.bureau.model.dto.request.project.PatchProjectRequest;
import com.bureau.model.dto.response.ProjectResponse;
import com.bureau.model.entity.Project;
import com.bureau.repository.ProjectRepository;
import com.bureau.util.ErrorStatusCodes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final CityService cityService;
    private final ClientService clientService;
    private final UserService userService;
    private final ProjectTypeService projectTypeService;
    private final ProjectMapper projectMapper;

    public Page<ProjectResponse> findPage(Pageable pageable) {
        return projectRepository.findAll(pageable).map(projectMapper::projectToProjectResponse);
    }

    public ProjectResponse findById(Long id) {
        return projectMapper.projectToProjectResponse(findOrThrow(id));
    }

    public void create(ProjectDto projectDto) {
        Project project = projectMapper.projectDtoToProject(projectDto);
        project.setDate(new Date());
        project.setActive(true);
        project.setCity(cityService.findOrThrow(projectDto.getCityId()));
        project.setClient(clientService.findOrThrow(projectDto.getClientId()));
        project.setType(projectTypeService.findOrThrow(projectDto.getTypeId()));
        projectRepository.save(project);
    }

    public void update(Long id, ProjectDto projectDto) {
        Project project = findOrThrow(id);
        projectMapper.updateProjectWithProjectDto(projectDto, project);
        projectRepository.save(project);
    }

    public void patch(Long id, PatchProjectRequest patchProjectRequest) {
        Project project = findOrThrow(id);
        project.setActive(patchProjectRequest.isActive());
        projectRepository.save(project);
    }

    public void delete(Long id) {
        projectRepository.deleteById(id);
    }

    public List<Project> getUserProjects(Long id) {
        return projectRepository.findUserProjects(id);
    }

    private Project findOrThrow(Long id) {
        return projectRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Project with id " + id + "not found", ErrorStatusCodes.PROJECT_NOT_FOUND));
    }

    public List<ProjectResponse> getAll() {
        return projectRepository.findAll().stream().map(projectMapper::projectToProjectResponse).toList();
    }

    public List<ProjectResponse> getByDatesBetween(LocalDateTime startDate, LocalDateTime endDate) {

        Date start = Date.from(startDate
                .atZone(ZoneId.systemDefault())
                .toInstant());
        Date end = Date.from(endDate
                .atZone(ZoneId.systemDefault())
                .toInstant());

        return projectRepository.findProjectsBetweenTwoDates(start, end).stream().map(projectMapper::projectToProjectResponse).toList();
    }
}
