package com.bureau.service;

import com.bureau.mapper.ProjectMapper;
import com.bureau.model.dto.request.project.PatchProjectRequest;
import com.bureau.model.dto.response.ProjectResponse;
import com.bureau.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProjectService {
    private final UserService userService;
    private final ProjectService projectService;
    private final ProjectMapper projectMapper;

    public List<ProjectResponse> findUserProjects() {
        return projectMapper.projectListToProjectResponseList(projectService.getUserProjects(userService.getUserFromJwt().getId()));
    }

    public void patchProject(Long id, PatchProjectRequest patchProjectRequest) {
        projectService.patch(id, patchProjectRequest);
    }
}
