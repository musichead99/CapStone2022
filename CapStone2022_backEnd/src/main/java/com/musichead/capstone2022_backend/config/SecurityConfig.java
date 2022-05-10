package com.musichead.capstone2022_backend.config;

import com.musichead.capstone2022_backend.config.auth.CustomOAuth2UserService;
import com.musichead.capstone2022_backend.config.auth.OAuth2AuthenticationSuccessHandler;
import com.musichead.capstone2022_backend.config.auth.RestAuthenticationEntryPoint;
import com.musichead.capstone2022_backend.config.auth.TokenAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                .httpBasic().disable()
//                .exceptionHandling().authenticationEntryPoint(new RestAuthenticationEntryPoint())
//                .and()
                .authorizeRequests()
                .antMatchers("/h2-console/**", "/audio/**", "/auth/**", "/oauth2/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                .userInfoEndpoint().userService(customOAuth2UserService)
                .and()
                .successHandler(oAuth2AuthenticationSuccessHandler);

        http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
