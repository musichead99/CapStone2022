package com.musichead.capstone2022_backend.config.auth;

import com.musichead.capstone2022_backend.config.auth.dto.CustomOAuth2User;
import com.musichead.capstone2022_backend.domain.member.Member;
import com.musichead.capstone2022_backend.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email : " + email));
        return CustomOAuth2User.builder().member(member).build();
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id : " + id));
        return CustomOAuth2User.builder().member(member).build();
    }
}
