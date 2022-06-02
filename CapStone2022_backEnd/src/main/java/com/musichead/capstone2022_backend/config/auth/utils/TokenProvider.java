package com.musichead.capstone2022_backend.config.auth.utils;

import com.musichead.capstone2022_backend.AppProperties;
import com.musichead.capstone2022_backend.config.auth.dto.CustomOAuth2User;
import com.musichead.capstone2022_backend.config.auth.exceptions.OAuth2AuthenticationProcessingException;
import io.jsonwebtoken.*;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Date;

@AllArgsConstructor
@Service
public class TokenProvider {

    private AppProperties appProperties;

    public String createToken(Authentication authentication) {
        CustomOAuth2User user = (CustomOAuth2User) authentication.getPrincipal();
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + appProperties.getAuth().getTokenExpirationTime());

        return Jwts.builder()
                .setSubject(Long.toString(user.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, appProperties.getAuth().getTokenSecret())
                .compact();

    }

    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(appProperties.getAuth().getTokenSecret())
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(appProperties.getAuth().getTokenSecret()).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            throw new OAuth2AuthenticationProcessingException("not valid jwt signature");
        } catch (MalformedJwtException e) {
            throw new OAuth2AuthenticationProcessingException("not valid jwt");
        } catch (ExpiredJwtException e) {
            throw new OAuth2AuthenticationProcessingException("expired jwt");
        } catch (UnsupportedJwtException e) {
            throw new OAuth2AuthenticationProcessingException("unsupported jwt");
        } catch (IllegalArgumentException e) {
            throw new OAuth2AuthenticationProcessingException("empty jwt");
        }
    }
}
