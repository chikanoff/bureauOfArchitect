package com.bureau.controller;

import com.bureau.model.dto.request.auth.SignInRequest;
import com.bureau.model.dto.response.JwtResponse;
import com.bureau.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication API")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<?> login(@RequestBody SignInRequest req) {
        return authService.login(req);
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logout(@CookieValue(name = "accessToken", required = false) String accessToken) {
        return authService.logout(accessToken);
    }

    @GetMapping("/check")
    public ResponseEntity<Boolean> check(@CookieValue(name = "accessToken", required = false) String accessToken) {
        return authService.check(accessToken);
    }

    @GetMapping("/currentUser")
    public ResponseEntity<JwtResponse> currentUser() {
        return authService.currentUser();
    }
}
