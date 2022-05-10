package com.bureau.jwt;

import com.bureau.IntegrationTestBase;
import com.bureau.model.dto.request.auth.SignInRequest;
import com.bureau.model.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.shaded.com.google.common.net.HttpHeaders;

import javax.servlet.http.Cookie;
import java.util.Objects;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class JwtAuthFilterTest extends IntegrationTestBase {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void authFilterTokenReturnsStatusOk() throws Exception {
        User user = createTestUser();
        SignInRequest req = new SignInRequest();
        req.setUsername(user.getUsername());
        req.setPassword("password");

        MvcResult result = mvc.perform(
                        post("/api/auth/signin")
                                .contentType("application/json")
                                .param("sendWelcomeMail", "true")
                                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk()).andReturn();

        String token = Objects.requireNonNull(result.getResponse().getCookie("accessToken")).getValue();

        mvc.perform(get("/api/test/")
                        .accept(MediaType.APPLICATION_JSON)
                        .cookie(new Cookie("accessToken", token)))
                .andExpect(status().isOk());

    }
}
