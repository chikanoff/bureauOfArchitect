package com.bureau.service;

import com.bureau.model.dto.request.user.CreateUserRequest;
import com.bureau.model.entity.User;
import com.bureau.repository.UserRepository;
import com.bureau.service.UserAdminService;
import liquibase.pro.packaged.U;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class UserAdminServiceUnitTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserAdminService userAdminService;

    @Test
    public void createUserReturnsEmailExist() {
        User user = User.builder().fullName("fullName")
                .username("username")
                .email("email@ia.co")
                .password("password")
                .build();
        when(userRepository.existsByEmail(user.getEmail())).thenReturn(true);

        CreateUserRequest req = new CreateUserRequest();
        req.setFullName(user.getFullName());
        req.setUsername(user.getUsername());
        req.setEmail(user.getEmail());
        req.setPassword(user.getPassword());

        assertThatThrownBy(() -> userAdminService.createUser(req)).hasMessage("User with this email already exist");

    }

    @Test
    public void createUserReturnsUsernameExist() {
        User user = User.builder().fullName("fullName")
                .username("username")
                .email("email@ia.co")
                .password("password")
                .build();
        when(userRepository.existsByUsername(user.getUsername())).thenReturn(true);

        CreateUserRequest req = new CreateUserRequest();
        req.setFullName(user.getFullName());
        req.setUsername(user.getUsername());
        req.setEmail(user.getEmail());
        req.setPassword(user.getPassword());

        assertThatThrownBy(() -> userAdminService.createUser(req)).hasMessage("User with this username already exist");
    }
}
