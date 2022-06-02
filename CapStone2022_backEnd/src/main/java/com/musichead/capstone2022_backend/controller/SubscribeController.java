package com.musichead.capstone2022_backend.controller;

import com.musichead.capstone2022_backend.domain.member.Member;
import com.musichead.capstone2022_backend.domain.subscribe.Subscribe;
import com.musichead.capstone2022_backend.dto.SubscribeOutPutDto;
import com.musichead.capstone2022_backend.service.SubscribeService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class SubscribeController {

    private final SubscribeService subscribeService;

    @QueryMapping
    public boolean isSubscribed(@Argument Long fromMemberId, @Argument Long toMemberId) {
        return subscribeService.isSubscribed(fromMemberId, toMemberId);
    }

    @QueryMapping
    public Long getSubscriberCount(@Argument Long toMemberId) {
        return subscribeService.countByToMember(toMemberId);
    }

    @MutationMapping
    public SubscribeOutPutDto addSubscribe(@Argument Long fromMemberId, @Argument Long toMemberId) {
        return subscribeService.save(fromMemberId, toMemberId);
    }

    @MutationMapping
    public SubscribeOutPutDto deleteSubscribe(@Argument Long fromMemberId, @Argument Long toMemberId) {
        return subscribeService.delete(fromMemberId, toMemberId);
    }
}
