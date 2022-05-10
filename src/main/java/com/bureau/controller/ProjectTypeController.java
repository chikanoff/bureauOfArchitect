package com.bureau.controller;

import com.bureau.model.dto.request.entities.ProjectTypeDto;
import com.bureau.model.dto.response.ProjectTypeResponse;
import com.bureau.model.entity.ProjectType;
import com.bureau.service.ProjectTypeService;
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
import java.util.List;

@RestController
@RequestMapping("/api/admin/type")
@PreAuthorize("hasRole('ROLE_ADMIN')")
@Tag(name = "Project types configuration API")
@SecurityRequirement(name = "security")
@RequiredArgsConstructor
public class ProjectTypeController {
    private final ProjectTypeService projectTypeService;

    @GetMapping
    public Page<ProjectTypeResponse> getPage(Pageable pageable) {
        return projectTypeService.findPage(pageable);
    }

    @GetMapping("/all")
    public List<ProjectTypeResponse> getAll() {
        return projectTypeService.getAll();
    }
    @GetMapping("{id}")
    public ProjectTypeResponse getById(@PathVariable("id") Long id) {
        return projectTypeService.findById(id);
    }

    @PostMapping
    public void create(@Valid @RequestBody ProjectTypeDto projectTypeDto) {
        projectTypeService.create(projectTypeDto);
    }

    @PutMapping("{id}")
    public void update(@PathVariable("id") Long id, @Valid @RequestBody ProjectTypeDto projectTypeDto) {
        projectTypeService.update(id, projectTypeDto);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        projectTypeService.delete(id);
    }
}
