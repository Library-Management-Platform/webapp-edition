package com.platform.libraryManager.providers;

import com.platform.libraryManager.helpers.DateHelper;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.time.Instant;

public class JWTProvider {

    private final SecretKey secretKey;
    private final long expirationMs;
    private final String issuer;

    public JWTProvider(
            SecretKey secretKey,
            long expirationMs,
            String issuer
    ) {
        this.secretKey = secretKey;
        this.expirationMs = expirationMs;
        this.issuer = issuer;
    }



    public String generateToken(String payload) {
        Instant issuedAt = DateHelper.getCurrentInstant();
        Instant expiry = DateHelper.plusSeconds(issuedAt, expirationMs / 1000);

        return Jwts.builder()
                .claim("payload", payload)   // subject
                .claim("iss", issuer) // issuer
                .issuedAt(DateHelper.getCurrentDate())        // issued at
                .expiration(DateHelper.from(expiry))   // expiry
                .signWith(secretKey)
                .compact();
    }

}
