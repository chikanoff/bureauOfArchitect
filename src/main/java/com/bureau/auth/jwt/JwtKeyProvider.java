package com.bureau.auth.jwt;

import com.bureau.auth.jwt.model.exception.JwtInitializationException;
import com.bureau.properties.JwtProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.function.BiFunction;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class JwtKeyProvider {
    @Getter
    private PrivateKey privateKey;

    private final JwtProperties jwtProperties;

    @Getter
    private PublicKey publicKey;

    @PostConstruct
    public void init() {
        privateKey = readKey(
                jwtProperties.getPrivateKey(),
                "PRIVATE",
                this::privateKeySpec,
                this::privateKeyGenerator
        );
        publicKey = readKey(
                jwtProperties.getPublicKey(),
                "PUBLIC",
                this::publicKeySpec,
                this::publicKeyGenerator
        );
    }

    private <T extends Key> T readKey(String key, String headerSpec, Function<String, EncodedKeySpec> keySpec,
                                      BiFunction<KeyFactory, EncodedKeySpec, T> keyGenerator) {
        try {
            key = key.replace("-----BEGIN " + headerSpec + " KEY-----", "")
                    .replace("-----END " + headerSpec + " KEY-----", "")
                    .replaceAll("\\s+", "");
            return keyGenerator.apply(KeyFactory.getInstance("RSA"), keySpec.apply(key));
        } catch (NoSuchAlgorithmException e) {
            throw new JwtInitializationException(e);
        }
    }

    private EncodedKeySpec privateKeySpec(String data) {
        return new PKCS8EncodedKeySpec(Base64.getDecoder().decode(data));
    }

    private PrivateKey privateKeyGenerator(KeyFactory kf, EncodedKeySpec spec) {
        try {
            return kf.generatePrivate(spec);
        } catch (InvalidKeySpecException e) {
            throw new JwtInitializationException(e);
        }
    }

    private EncodedKeySpec publicKeySpec(String data) {
        return new X509EncodedKeySpec(Base64.getDecoder().decode(data));
    }

    private PublicKey publicKeyGenerator(KeyFactory kf, EncodedKeySpec spec) {
        try {
            return kf.generatePublic(spec);
        } catch (InvalidKeySpecException e) {
            throw new JwtInitializationException(e);
        }
    }
}
