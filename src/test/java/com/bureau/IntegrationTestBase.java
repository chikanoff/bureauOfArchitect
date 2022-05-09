package com.bureau;

import com.bureau.model.entity.Client;
import com.bureau.model.entity.Project;
import com.bureau.model.entity.ProjectType;
import com.bureau.model.entity.User;
import com.bureau.model.entity.City;
import com.bureau.model.entity.UserRole;
import com.bureau.repository.CityRepository;
import com.bureau.repository.ClientRepository;
import com.bureau.repository.ProjectRepository;
import com.bureau.repository.ProjectTypeRepository;
import com.bureau.repository.UserRepository;
import com.bureau.repository.UserRoleRepository;
import lombok.Getter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
import java.util.List;
import java.util.Set;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@AutoConfigureWebTestClient
@Getter
public class IntegrationTestBase {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private ProjectTypeRepository projectTypeRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private PasswordEncoder encoder;

    public User createTestUser() {
        userRoleRepository.save(UserRole.builder().name("ROLE_USER").build());
        return userRepository.save(
                User.builder()
                        .fullName("testFullName")
                        .username("test")
                        .email("testEmail@gmail.com")
                        .password(encoder.encode("password"))
                        .role(userRoleRepository.findByName("ROLE_USER"))
                        .build());
    }

    public User createTestAdmin() {
        userRoleRepository.save(UserRole.builder().name("ROLE_ADMIN").build());
        return userRepository.save(
                User.builder()
                        .fullName("testAdminFullName")
                        .username("testAdmin")
                        .email("admin@gmail.com")
                        .password(encoder.encode("password"))
                        .role(userRoleRepository.findByName("ROLE_ADMIN"))
                        .build());
    }

    public ProjectType createProjectType() {
        return projectTypeRepository.save(ProjectType.builder().name("testProjectType").build());
    }

    public Project createTestProject() {
        return projectRepository.save(Project.builder()
                .name("build a town")
                .address("Kirov")
                .date(new Date())
                .notes("notes")
                .projectUrl("url")
                .active(true)
                .type(createProjectType())
                .city(createTestCity())
                .client(createTestClient())
                .users(Set.of(createTestUser()))
                .build());
    }

    public Client createTestClient() {
        return clientRepository.save(Client.builder()
                .fullName("testFullName")
                .email("clientEmail@gmail.com")
                .phone("+375446574354")
                .organization("OAO buildings")
                .build());
    }

    public City createTestCity() {
        return cityRepository.save(City.builder()
                .name("testCity")
                .build());
    }

    @BeforeEach
    public void resetDb() {
        userRepository.deleteAll();
        userRoleRepository.deleteAll();
        projectTypeRepository.deleteAll();
        projectRepository.deleteAll();
        clientRepository.deleteAll();
        cityRepository.deleteAll();
    }
}
