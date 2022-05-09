package com.bureau.controller;

import com.bureau.exception.ProjectAssignationException;
import com.bureau.model.dto.request.project.PatchProjectRequest;
import com.bureau.model.dto.request.user.PatchUserRequest;
import com.bureau.model.dto.response.ProjectResponse;
import com.bureau.model.dto.response.UserInfoResponse;
import com.bureau.service.ProjectService;
import com.bureau.service.UserProjectService;
import com.bureau.service.UserService;
import com.bureau.util.ErrorStatusCodes;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
@Tag(name = "Users configuration API")
@SecurityRequirement(name = "security")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserProjectService userProjectService;
    private final ProjectService projectService;

    @GetMapping
    public UserInfoResponse getUserInfo() {
        return userService.getUserInfo();
    }

    @PatchMapping
    public void changePassword(@Valid @RequestBody PatchUserRequest req) {
        userService.changePassword(req);
    }

    @GetMapping("/projects")
    public List<ProjectResponse> getUserProjects() {
        return userProjectService.findUserProjects();
    }

    @PatchMapping("/project/{id}")
    public void patchProject(@PathVariable Long id, @RequestBody PatchProjectRequest req) {
        if(projectService.getUserProjects(userService.getUserFromJwt().getId())
                .stream().noneMatch(p -> p.getId().equals(id))) {
            throw new ProjectAssignationException("You don't have access to this project", ErrorStatusCodes.PROJECT_ASSIGNEE_NOT_FOUND);
        }
        userProjectService.patchProject(id, req);
    }
}
