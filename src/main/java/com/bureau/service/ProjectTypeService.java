package com.bureau.service;

import com.bureau.exception.ItemNotFoundException;
import com.bureau.mapper.ProjectTypeMapper;
import com.bureau.model.dto.request.entities.ProjectTypeDto;
import com.bureau.model.dto.response.ProjectTypeResponse;
import com.bureau.model.entity.ProjectType;
import com.bureau.repository.ProjectTypeRepository;
import com.bureau.util.ErrorStatusCodes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectTypeService {
    private final ProjectTypeRepository projectTypeRepository;
    private final ProjectTypeMapper projectTypeMapper;

    public Page<ProjectTypeResponse> findPage(Pageable pageable) {
        return projectTypeRepository.findAll(pageable).map(projectTypeMapper::projectTypeToProjectTypeResponse);
    }

    public ProjectTypeResponse findById(Long id) {
        return projectTypeMapper.projectTypeToProjectTypeResponse(findOrThrow(id));
    }

    public void create(ProjectTypeDto projectTypeDto) {
        projectTypeRepository.save(projectTypeMapper.projectTypeDtoToProjectType(projectTypeDto));
    }

    public void update(Long id, ProjectTypeDto projectTypeDto) {
        ProjectType projectType = findOrThrow(id);
        projectTypeMapper.updateTypeWithTypeDto(projectTypeDto, projectType);
        projectTypeRepository.save(projectType);
    }
    public void delete(Long id) {
        projectTypeRepository.deleteById(id);
    }

    public ProjectType findOrThrow(Long id) {
        return projectTypeRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Project type with id" + id + "not found", ErrorStatusCodes.PROJECT_TYPE_NOT_FOUND));
    }

    public List<ProjectTypeResponse> getAll() {
        return projectTypeRepository.findAll().stream().map(projectTypeMapper::projectTypeToProjectTypeResponse).toList();
    }
}
