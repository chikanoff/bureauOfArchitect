package com.bureau.service;

import com.bureau.auth.jwt.JwtService;
import com.bureau.model.dto.request.auth.SignInRequest;
import com.bureau.model.dto.response.JwtResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    /**
     * Returns jwt result of authentication
     *
     * @param req Request with username and password fields
     * @return JwtResponse
     */
    public JwtResponse login(SignInRequest req) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));

        String jwt = jwtService.generateToken((UserDetails) authentication.getPrincipal());

        return new JwtResponse(jwt);
    }
}
