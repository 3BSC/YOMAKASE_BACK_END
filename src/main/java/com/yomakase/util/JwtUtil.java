package com.yomakase.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yomakase.etc.enums.ExceptionMessage;
import com.yomakase.etc.enums.TokenType;
import com.yomakase.etc.exception.NonCriticalException;
import com.yomakase.user.enums.UserType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private Key jwtKey;

    @Value("${jwt.access}")
    private String accessString;

    @Value("${jwt.refresh}")
    private String refreshString;

    public static final short BEARER_LENGTH = 7;

    public JwtUtil(@Value("${jwt.key}") String key) {
        this.jwtKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(Long id, UserType type) {
        return generateToken(id, "auth", type.toString(), TokenType.ACCESS);
    }

    public String generateRefreshToken(Long id, String uuid) {
        return generateToken(id, "dev", uuid, TokenType.REFRESH);
    }

    private String generateToken(Long id, String key, Object value, TokenType type) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", "HS256");

        Map<String, Object> payloads = new HashMap<>();
        payloads.put("id", id);
        payloads.put(key, value);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        calendar.add(Calendar.HOUR_OF_DAY, type.getTokenRemainTime());
        Date expirationTime = calendar.getTime();

        return Jwts.builder()
                .setHeader(headers)
                .setClaims(payloads)
                .setSubject(type.isAccess() ? accessString : refreshString)
                .setExpiration(expirationTime)
                .signWith(jwtKey)
                .compact();
    }

    public boolean isValid(String token, TokenType tokenType) throws Exception {
        if (token == null || token.length() < BEARER_LENGTH + 1 || !token.startsWith("Bearer ")) {
            throw new NonCriticalException(ExceptionMessage.INVALID_TOKEN_TYPE);
        }

        Claims claims = null;
        try {
            claims = Jwts.parserBuilder()
                    .setSigningKey(jwtKey)
                    .build()
                    .parseClaimsJws(token.substring(BEARER_LENGTH))
                    .getBody();
        } catch (ExpiredJwtException expiredJwtException) {
            throw new NonCriticalException(ExceptionMessage.EXPIRED_TOKEN);
        } catch (JwtException e) {
            throw new NonCriticalException(ExceptionMessage.INVALID_TOKEN);
        }

        if (claims.getSubject() == null || claims.get("id", Long.class) == null) {
            throw new NonCriticalException(ExceptionMessage.INVALID_TOKEN);
        }

        if (!claims.getSubject().equals(tokenType.isAccess() ? accessString : refreshString)) {
            throw new NonCriticalException(ExceptionMessage.INVALID_TOKEN);
        }

        return true;
    }

    public Map<String, Object> getPayloadsFromJwt(String token) throws Exception {
        String[] chunks = token.split("\\.");
        String payloads = new String(Base64.getDecoder().decode(chunks[1]));

        HashMap<String, Object> map = null;
        try {
            map = new ObjectMapper().readValue(payloads, HashMap.class);
        } catch (JsonProcessingException e) {
            throw new NonCriticalException(ExceptionMessage.INVALID_TOKEN);
        }
        return map;
    }
}
