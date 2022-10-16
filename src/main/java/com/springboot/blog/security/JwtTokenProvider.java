package com.springboot.blog.security;

import com.springboot.blog.exception.BlogAPIException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private String jwtExpirationInMs;

    //generate token
    public String generateToken(Authentication authentication) {
        String userName = authentication.getName();
        Date currentDate = new Date();
        Long time= Long.valueOf(currentDate.getTime() + jwtExpirationInMs);
        Date expireDate = new Date(time);
        String token = Jwts.builder().setSubject(userName).setIssuedAt(new Date()).
                setExpiration(expireDate).signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
        return token;
    }

    //get username from token
    public String getUseNameFromJWT(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    //validate jwt token
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Invalid jwt signature");
        } catch (MalformedJwtException mx) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Invalid jwt token");
        } catch (ExpiredJwtException jx) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Expired jwt token");
        } catch (UnsupportedJwtException ux) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "unsupported jwt token");
        } catch (IllegalArgumentException ix) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "jwt claim string is empty");
        }

    }
}
