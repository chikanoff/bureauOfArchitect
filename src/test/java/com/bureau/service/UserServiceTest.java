package com.bureau.service;

import com.bureau.IntegrationTestBase;
import com.bureau.model.dto.request.user.PatchUserRequest;
import com.bureau.model.dto.response.UserInfoResponse;
import com.bureau.model.entity.User;
import com.bureau.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UserServiceTest extends IntegrationTestBase {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void changePasswordTest() {
        User user = createTestUser();

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), null));
        PatchUserRequest req = new PatchUserRequest();
        String newPassword = "newPassword";
        req.setPassword(newPassword);
        userService.changePassword(req);
        User changedUser = getUserRepository().findByUsername(user.getUsername()).get();
        assertThat(passwordEncoder.matches(newPassword, changedUser.getPassword())).isTrue();

    }

    @Test
    public void getUserInfoTest() {
        User user = createTestUser();

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), null));
        UserInfoResponse response = userService.getUserInfo();
        assertThat(response.getUsername()).isEqualTo(user.getUsername());
        assertThat(response.getFullName()).isEqualTo(user.getFullName());
        assertThat(response.getEmail()).isEqualTo(user.getEmail());
        assertThat(response.getRole().getName()).isEqualTo(user.getRole().getName());
    }
}
