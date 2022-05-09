package com.bureau.controller;

import com.bureau.model.dto.request.entities.ProjectDto;
import com.bureau.model.dto.request.project.ProjectAssignationRequest;
import com.bureau.model.dto.request.user.CreateUserRequest;
import com.bureau.model.dto.response.ProjectResponse;
import com.bureau.service.ProjectAssignationService;
import com.bureau.service.ProjectService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin/project")
@PreAuthorize("hasRole('ROLE_ADMIN')")
@Tag(name = "Project configuration API")
@SecurityRequirement(name = "security")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    private final ProjectAssignationService projectAssignationService;

    @GetMapping
    public Page<ProjectResponse> getPage(Pageable pageable) {
        return projectService.findPage(pageable);
    }

    @GetMapping("/{id}")
    public ProjectResponse getProject(@PathVariable Long id) {
        return projectService.findById(id);
    }

    @PostMapping
    public void create(@Valid @RequestBody ProjectDto projectDto) {
        projectService.create(projectDto);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @Valid @RequestBody ProjectDto projectDto) {
        projectService.update(id, projectDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        projectService.delete(id);
    }

    @PostMapping("/{id}/assignation")
    public void assign(@PathVariable Long id, @Valid @RequestBody ProjectAssignationRequest req) {
        projectAssignationService.assignUserToProject(id, req);
    }
}
