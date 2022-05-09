package com.bureau.controller;

import com.bureau.model.dto.request.user.CreateUserRequest;
import com.bureau.model.dto.request.user.PatchUserRequest;
import com.bureau.model.dto.request.user.UpdateUserRequest;
import com.bureau.model.dto.response.UserResponse;
import com.bureau.service.UserAdminService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin/users")
@PreAuthorize("hasRole('ROLE_ADMIN')")
@Tag(name = "Admin users configuration API")
@SecurityRequirement(name = "security")
@RequiredArgsConstructor
public class UserAdminController {
    private final UserAdminService userAdminService;

    @GetMapping("/{id}")
    public UserResponse getById(@PathVariable("id") Long id) {
        return userAdminService.getById(id);
    }

    @GetMapping
    public Page<UserResponse> getPage(Pageable pageable) {
        return userAdminService.getPage(pageable);
    }

    @PostMapping
    public void create(@Valid @RequestBody CreateUserRequest item) {
        userAdminService.createUser(item);
    }

    @PatchMapping("/{id}")
    public void changePassword(@PathVariable("id") Long id, @Valid @RequestBody PatchUserRequest req) {
        userAdminService.changePassword(id, req);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long id, @Valid @RequestBody UpdateUserRequest req) {
        userAdminService.update(id, req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        userAdminService.delete(id);
    }
}
