package com.bureau.service;

import com.bureau.auth.jwt.model.UserDetailsServiceImpl;
import com.bureau.model.entity.User;
import com.bureau.model.entity.UserRole;
import com.bureau.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Test
    public void findByUsernameIfNotExist() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
        assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername("username"));
        verify(userRepository).findByUsername(anyString());
    }
    @Test
    public void findByExistUsername() {
        User user = User.builder().username("username")
                                  .fullName("test")
                                  .email("test")
                                  .password("password")
                                  .role(UserRole.builder().name("ROLE_USER").build())
                        .build();
        when(userRepository.findByUsername("username")).thenReturn(Optional.of(user));
        assertEquals(user.getUsername(), userDetailsService.loadUserByUsername("username").getUsername());
    }
}
