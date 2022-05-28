package com.musichead.capstone2022_backend.dto;

import com.musichead.capstone2022_backend.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SubscribeOutPutDto {
    private Member fromMember;
    private Member toMember;
    private Long subscriberCount;
}
