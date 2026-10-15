package com.example.simplejwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api/jwt")
public class JwtController {

    private final SecretKey signingKey;

    public JwtController(@Value("${jwt.secret}") String base64Secret) {
        this.signingKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(base64Secret));
    }

    @PostMapping("/generate")
    public Map<String, String> generateToken(@RequestBody UserRequest user) {
        Instant now = Instant.now();

        String token = Jwts.builder()
                .subject(user.username())
                .claim("role", user.role())
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusSeconds(3600)))
                .signWith(signingKey)
                .compact();

        return Map.of("token", token);
    }

    public record UserRequest(String username, String role) {}
}
