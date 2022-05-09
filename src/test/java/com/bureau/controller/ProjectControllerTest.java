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

import java.util.Date;
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

        return result.getResponse().getContentAsString()
                .replace("{\"token\":\"", "")
                .replace("\"}", "");
    }

    @Test
    public void createProject() throws Exception {
        String token = getTokenFromAuthorization();
        ProjectDto dto = new ProjectDto();
        dto.setName("test");
        dto.setNotes("notes");
        dto.setDate(new Date());
        dto.setAddress("address");
        dto.setActive(true);
        dto.setProjectUrl("url");
        dto.setTypeId(createProjectType().getId());
        dto.setClientId(createTestClient().getId());
        dto.setUserIds(Set.of(createTestUser().getId()));
        dto.setCityId(createTestCity().getId());

        mvc.perform(
                        post("/api/admin/project/")
                                .contentType("application/json")
                                .header("Authorization", "Bearer " + token)
                                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteProjectTest() throws Exception {
        Project proj = createTestProject();
        String token = getTokenFromAuthorization();
        mvc.perform(delete("/api/admin/project/" + proj.getId())
                        .contentType("application/json")
                        .header("Authorization", "Bearer " + token)
                ).andExpect(status().isOk());
    }
}
