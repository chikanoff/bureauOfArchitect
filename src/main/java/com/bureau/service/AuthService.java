package com.bureau.service;

import com.bureau.auth.jwt.JwtService;
import com.bureau.model.dto.request.auth.SignInRequest;
import com.bureau.model.dto.response.JwtResponse;
import com.bureau.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CookieValue;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final UserService userService;

    public ResponseEntity<?> login(SignInRequest req) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwt = jwtService.generateToken(userDetails);
        HttpHeaders headers = new HttpHeaders();
        jwtService.addTokenToCookie(headers, jwt);
        try {
            User user = userService.findByUsername(userDetails.getUsername());
            return ResponseEntity.ok().headers(headers).body(new JwtResponse(user.getId(),
                    userDetails.getUsername(),
                    userDetails.getAuthorities()
                            .stream()
                            .anyMatch(x -> x.getAuthority().equals("ROLE_ADMIN"))));
        } catch(Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    public ResponseEntity<Boolean> check(@CookieValue(name = "accessToken") String accessToken) {
        return ResponseEntity.ok(jwtService.validateToken(accessToken));
    }

    public ResponseEntity<JwtResponse> currentUser() {
        User user = userService.getUserFromJwt();
        if(user == null) {
            return ResponseEntity.ok(new JwtResponse());
        }
        return ResponseEntity.ok(new JwtResponse(user.getId(), user.getUsername(), user.getRole().getName().equals("ROLE_ADMIN")));
    }

    public ResponseEntity<String> logout(@CookieValue(name = "accessToken") String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        jwtService.invalidateToken(headers, accessToken);
        return ResponseEntity.ok().headers(headers).body("success");
    }
}
