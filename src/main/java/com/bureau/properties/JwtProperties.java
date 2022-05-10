package com.bureau.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private Duration expiration;
    private String privateKey;
    private String publicKey;
    private String accessToken;
}
