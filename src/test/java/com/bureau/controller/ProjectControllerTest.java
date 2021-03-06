package com.bureau.controller;

import com.bureau.IntegrationTestBase;
import com.bureau.model.dto.request.auth.SignInRequest;
import com.bureau.model.dto.request.entities.ProjectDto;
import com.bureau.model.entity.Project;
import com.bureau.model.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.servlet.http.Cookie;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProjectControllerTest extends IntegrationTestBase {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    private String getTokenFromAuthorization() throws Exception {
        User admin = createTestAdmin();
        SignInRequest req = new SignInRequest();
        req.setUsername(admin.getUsername());
        req.setPassword("password");
        MvcResult result = mvc.perform(
                        post("/api/auth/signin")
                                .contentType("application/json")
                                .param("sendWelcomeMail", "true")
                                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk()).andReturn();

        return Objects.requireNonNull(result.getResponse().getCookie("accessToken")).getValue();
    }

    @Test
    public void createProject() throws Exception {
        String token = getTokenFromAuthorization();
        ProjectDto dto = new ProjectDto();
        dto.setName("test");
        dto.setNotes("notes");
        dto.setAddress("address");
        dto.setProjectUrl("url");
        dto.setTypeId(createProjectType().getId());
        dto.setClientId(createTestClient().getId());
        dto.setCityId(createTestCity().getId());

        mvc.perform(
                        post("/api/admin/project/")
                                .contentType("application/json")
                                .cookie(new Cookie("accessToken", token))
                                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteProjectTest() throws Exception {
        Project proj = createTestProject();
        String token = getTokenFromAuthorization();
        mvc.perform(delete("/api/admin/project/" + proj.getId())
                        .contentType("application/json")
                        .cookie(new Cookie("accessToken", token))
                ).andExpect(status().isOk());
    }
}
