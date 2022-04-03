package com.musichead.capstone2022_backend.service;

import com.musichead.capstone2022_backend.domain.user.Member;
import com.musichead.capstone2022_backend.domain.user.MemberRepository;
import com.musichead.capstone2022_backend.dto.MemberDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/* 테스트용 */
@AllArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Member findById(Long id) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        Member member = null;

        if(optionalMember.isPresent()) {
            member = optionalMember.get();
        }

        return member;
    }

    public Member save(MemberDto memberDto) {
        Member member = Member.builder()
                .email(memberDto.getEmail())
                .name(memberDto.getName())
                .picture(memberDto.getPicture())
                .build();

        return memberRepository.save(member);
    }
}