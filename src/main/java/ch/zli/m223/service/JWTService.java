package ch.zli.m223.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.HashSet;

import org.eclipse.microprofile.jwt.Claims;

import io.smallrye.jwt.build.Jwt;

public class JWTService {
    /**
     * Generate JWT token
     */
    public static void main(String[] args) {
        String token =
           Jwt.issuer("https://example.com/issuer")
             .upn("jdoe@quarkus.io")
             .groups(new HashSet<>(Arrays.asList("User", "Admin")))
             .claim(Claims.birthdate.name(), "2001-07-13")
                .expiresAt(Instant.now().plus(2, ChronoUnit.HOURS))
           .sign();
        System.out.println(token);
    }
}