package com.musichead.capstone2022_backend.config.auth.dto;

import com.musichead.capstone2022_backend.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Getter
public class CustomOAuth2User implements OAuth2User, UserDetails {
    private long id;
    private String email;
    private Collection<? extends GrantedAuthority> authorities;
    @Setter
    private Map<String, Object> attributes;

    @Builder
    public CustomOAuth2User(Member member,Map<String, Object> attributes) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.authorities = Collections.singletonList(new SimpleGrantedAuthority(member.getRoleKey()));
        this.attributes = attributes;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getName() {
        return Long.toString(id);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
