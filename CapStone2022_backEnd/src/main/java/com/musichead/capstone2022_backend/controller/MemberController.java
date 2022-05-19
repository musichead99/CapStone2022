package com.musichead.capstone2022_backend.controller;

import com.musichead.capstone2022_backend.domain.member.Member;
import com.musichead.capstone2022_backend.dto.MemberDto;
import com.musichead.capstone2022_backend.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;

    @QueryMapping
    public List<Member> members() {
        return memberService.findAll();
    }

    @QueryMapping
    public List<Member> getSubscriberList(@Argument Long id) {
        return memberService.findSubscribers(id);
    }

    @QueryMapping
    public Member getMember(@Argument Long id) {
        return memberService.findById(id);
    }

    @MutationMapping
    public Member addMember(@Argument MemberDto memberDto) {
        return memberService.save(memberDto);
    }

    @MutationMapping
    public Member updateMember(@Argument Long id, @Argument MemberDto memberDto) {
        return memberService.update(id, memberDto);
    }
}
