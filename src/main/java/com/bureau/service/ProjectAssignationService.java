package com.bureau.service;

import com.bureau.model.dto.request.project.ProjectAssignationRequest;
import com.bureau.repository.ProjectRepository;
import com.bureau.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectAssignationService {
    private final ProjectRepository projectRepository;
    private final UserService userService;

    public void assignUserToProject(Long id, ProjectAssignationRequest req) {
        projectRepository.findById(id).ifPresent(project -> {
            project.addUser(userService.findByIdOrThrow(req.getUserId()));
            projectRepository.save(project);
        });
    }
    public void removeUserFromProject(Long id, ProjectAssignationRequest req) {
        projectRepository.findById(id).ifPresent(project -> {
            project.removeUser(userService.findByIdOrThrow(req.getUserId()));
            projectRepository.save(project);
        });
    }
}
