package com.musichead.capstone2022_backend.service;

import com.musichead.capstone2022_backend.domain.member.Member;
import com.musichead.capstone2022_backend.domain.member.MemberRepository;
import com.musichead.capstone2022_backend.domain.subscribe.SubscribeRepository;
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
    private final SubscribeRepository subscribeRepository;

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

    public List<Member> findSubscribers(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("id=" + id + "member not exist"));

        return subscribeRepository.findToMemberByFromMember(member);
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
