package com.musichead.capstone2022_backend.service;

import com.musichead.capstone2022_backend.domain.post.Post;
import com.musichead.capstone2022_backend.domain.subscribe.Subscribe;
import com.musichead.capstone2022_backend.domain.subscribe.SubscribeRepository;
import com.musichead.capstone2022_backend.domain.member.Member;
import com.musichead.capstone2022_backend.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;
    private final MemberRepository memberRepository;

    public Long countByFromMember(Long fromMemberId) {
        Member fromMember = memberRepository.getReferenceById(fromMemberId);
        return subscribeRepository.countByFromMember(fromMember);
    }

    public Subscribe save(Long fromMemberId, Long toMemberId) {
        Member fromMember = memberRepository.getReferenceById(fromMemberId);
        Member toMember = memberRepository.getReferenceById(toMemberId);

        Subscribe subscribe = Subscribe.builder()
                .fromMember(fromMember)
                .toMember(toMember)
                .build();

        return subscribeRepository.save(subscribe);
    }

    public Subscribe delete(Long fromMemberId, Long toMemberId) {
        Member fromMember = memberRepository.getReferenceById(fromMemberId);
        Member toMember = memberRepository.getReferenceById(toMemberId);

        Subscribe subscribe = subscribeRepository.findByFromMemberAndToMember(fromMember, toMember)
                .orElseThrow(() -> new IllegalArgumentException("subscribe not exist"));

        subscribeRepository.delete(subscribe);
        return subscribe;
    }
}
