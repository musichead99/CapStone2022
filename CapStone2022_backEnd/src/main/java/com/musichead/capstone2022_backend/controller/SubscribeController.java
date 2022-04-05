package com.musichead.capstone2022_backend.controller;

import com.musichead.capstone2022_backend.domain.member.Member;
import com.musichead.capstone2022_backend.domain.subscribe.Subscribe;
import com.musichead.capstone2022_backend.service.SubscribeService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class SubscribeController {

    private final SubscribeService subscribeService;

    @MutationMapping
    public Subscribe addSubscribe(@Argument Long fromMemberId, @Argument Long toMemberId) {
        return subscribeService.save(fromMemberId, toMemberId);
    }
}
