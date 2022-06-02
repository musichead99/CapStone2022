package com.musichead.capstone2022_backend.service;

import com.musichead.capstone2022_backend.domain.post.Post;
import com.musichead.capstone2022_backend.domain.subscribe.Subscribe;
import com.musichead.capstone2022_backend.domain.subscribe.SubscribeRepository;
import com.musichead.capstone2022_backend.domain.member.Member;
import com.musichead.capstone2022_backend.domain.member.MemberRepository;
import com.musichead.capstone2022_backend.dto.SubscribeOutPutDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;
    private final MemberRepository memberRepository;

    public boolean isSubscribed(Long fromMemberId, Long toMemberId) {

        Member fromMember = memberRepository.getReferenceById(fromMemberId);
        Member toMember = memberRepository.getReferenceById(toMemberId);

        return subscribeRepository.findByFromMemberAndToMember(fromMember, toMember).isPresent();
    }

    public Long countByToMember(Long toMemberId) {
        Member toMember = memberRepository.getReferenceById(toMemberId);
        return subscribeRepository.countByToMember(toMember);
    }

    public SubscribeOutPutDto save(Long fromMemberId, Long toMemberId) {
        check(fromMemberId,toMemberId);

        Member fromMember = memberRepository.getReferenceById(fromMemberId);
        Member toMember = memberRepository.getReferenceById(toMemberId);

        Subscribe subscribe = Subscribe.builder()
                .fromMember(fromMember)
                .toMember(toMember)
                .build();
        subscribeRepository.save(subscribe);

        Long subscriberCount = subscribeRepository.countByToMember(toMember);

        return new SubscribeOutPutDto(fromMember, toMember, subscriberCount);
    }

    public SubscribeOutPutDto delete(Long fromMemberId, Long toMemberId) {
        check(fromMemberId,toMemberId);

        Member fromMember = memberRepository.getReferenceById(fromMemberId);
        Member toMember = memberRepository.getReferenceById(toMemberId);

        Subscribe subscribe = subscribeRepository.findByFromMemberAndToMember(fromMember, toMember)
                .orElseThrow(() -> new IllegalArgumentException("subscribe not exist"));

        subscribeRepository.delete(subscribe);
        Long subscriberCount = subscribeRepository.countByToMember(toMember);
        return new SubscribeOutPutDto(fromMember, toMember, subscriberCount);
    }

    private void check(Long fromMemberId, Long toMemberId) {
        if(fromMemberId.longValue() == toMemberId.longValue()) {
            throw new IllegalArgumentException("can't subscribe " + fromMemberId + "to " + toMemberId);
        }
    }
}
