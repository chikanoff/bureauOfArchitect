package com.bureau.controller;

import com.bureau.IntegrationTestBase;
import com.bureau.model.dto.request.auth.SignInRequest;
import com.bureau.model.dto.request.entities.CityDto;
import com.bureau.model.entity.User;
import com.bureau.model.entity.City;
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

public class CityControllerTest extends IntegrationTestBase {
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
    public void findPageTest() throws Exception {
        String token = getTokenFromAuthorization();
        mvc.perform(
                get("/api/admin/city/")
                        .contentType("application/json")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    public void createCityReturnsOk() throws Exception {
        String token = getTokenFromAuthorization();
        CityDto dto = new CityDto();
        dto.setName("test");
        mvc.perform(
                post("/api/admin/city/")
                        .contentType("application/json")
                        .header("Authorization", "Bearer " + token)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteCityReturnsOk() throws Exception {
        String token = getTokenFromAuthorization();
        City city = createTestCity();
        mvc.perform(
                delete("/api/admin/city/" + city.getId())
                        .contentType("application/json")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    public void updateCityReturnsOk() throws Exception {
        String token = getTokenFromAuthorization();
        City city = createTestCity();
        CityDto dto = new CityDto();
        dto.setName("new name");
        mvc.perform(
                put("/api/admin/city/" + city.getId())
                        .contentType("application/json")
                        .header("Authorization", "Bearer " + token)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }
}
