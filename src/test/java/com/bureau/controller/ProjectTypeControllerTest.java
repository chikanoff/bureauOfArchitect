package com.bureau.controller;

import com.bureau.IntegrationTestBase;
import com.bureau.model.dto.request.auth.SignInRequest;
import com.bureau.model.dto.request.entities.ProjectTypeDto;
import com.bureau.model.entity.ProjectType;
import com.bureau.model.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProjectTypeControllerTest extends IntegrationTestBase {
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
    public void findPageReturnsOk() throws Exception {
        mvc.perform(
                get("/api/admin/type/")
                        .contentType("application/json")
                        .header("Authorization", "Bearer " + getTokenFromAuthorization())
                        .param("page", "1")
                        .param("size", "10")
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    public void createTypeReturnsOk() throws Exception {
        ProjectTypeDto dto = new ProjectTypeDto();
        dto.setName("test");
        mvc.perform(
                post("/api/admin/type/")
                        .contentType("application/json")
                        .header("Authorization", "Bearer " + getTokenFromAuthorization())
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    public void updateTypeReturnsOk() throws Exception {
        ProjectType type = createProjectType();
        ProjectTypeDto dto = new ProjectTypeDto();
        dto.setName("new name");
        mvc.perform(
                put("/api/admin/type/" + type.getId())
                        .contentType("application/json")
                        .header("Authorization", "Bearer " + getTokenFromAuthorization())
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteTypeReturnsOk() throws Exception {
        ProjectType type = createProjectType();
        mvc.perform(
                delete("/api/admin/type/" + type.getId())
                        .contentType("application/json")
                        .header("Authorization", "Bearer " + getTokenFromAuthorization()))
                .andExpect(status().isOk());
    }
}
