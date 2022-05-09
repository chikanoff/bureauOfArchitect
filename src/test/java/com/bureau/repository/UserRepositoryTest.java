package com.bureau.repository;

import com.bureau.IntegrationTestBase;
import com.bureau.model.entity.User;
import com.bureau.repository.UserRepository;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserRepositoryTest extends IntegrationTestBase {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void findAll() {
        createTestUser();
        final int pageNumber = 0;
        final int pageSize = 3;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        assertEquals(userRepository.findAll(pageable).getTotalElements(), 1L);
    }

    @Test
    public void findByUsernameReturnsUser() {
        User user = createTestUser();
        User found = getUserRepository().findByUsername(user.getUsername()).get();

        assertThat(found.getFullName()).isEqualTo(user.getFullName());
        assertThat(found.getEmail()).isEqualTo(user.getEmail());
        assertThat(found.getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    public void existByEmailReturnsTrue() {
        User user = createTestUser();
        assertThat(getUserRepository().existsByEmail(user.getEmail())).isTrue();
    }

    @Test
    public void existByUsernameReturnsTrue() {
        User user = createTestUser();

        assertThat(getUserRepository().existsByUsername(user.getUsername())).isTrue();
    }
}
