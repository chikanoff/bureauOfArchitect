package com.bureau.service;

import com.bureau.IntegrationTestBase;
import com.bureau.model.dto.request.user.CreateUserRequest;
import com.bureau.model.entity.User;
import com.bureau.model.entity.UserRole;
import com.bureau.service.UserAdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UserAdminServiceIntegrationTest extends IntegrationTestBase {
    @Autowired
    private UserAdminService userAdminService;

    @Test
    public void createUserTest() {
        getUserRoleRepository().save(UserRole.builder().name("ROLE_USER").build());
        CreateUserRequest req = new CreateUserRequest();
        req.setFullName("user1");
        req.setUsername("user1");
        req.setEmail("user1@gmail.com");
        req.setPassword("password");
        req.setRoleId(getUserRoleRepository().findByName("ROLE_USER").getId());

        userAdminService.createUser(req);

        List<User> users = getUserRepository().findAll();
        User user = users.get(users.size() - 1);
        assertThat(user.getUsername()).isEqualTo(req.getUsername());
        assertThat(user.getEmail()).isEqualTo(req.getEmail());
    }

    @Test
    public void checkEmailExistTest() {
        User user = createTestUser();
        CreateUserRequest req = new CreateUserRequest();
        req.setFullName(user.getFullName());
        req.setUsername("username");
        req.setEmail(user.getEmail());
        req.setPassword("password");

        assertThatThrownBy(() -> userAdminService.createUser(req)).hasMessage("User with this email already exist");
    }

    @Test
    public void checkUsernameExistTest() {
        User user = createTestUser();
        CreateUserRequest req = new CreateUserRequest();
        req.setFullName(user.getFullName());
        req.setUsername(user.getUsername());
        req.setEmail("email");
        req.setPassword("password");

        assertThatThrownBy(() -> userAdminService.createUser(req)).hasMessage("User with this username already exist");
    }
}
