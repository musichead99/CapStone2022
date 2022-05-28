package com.musichead.capstone2022_backend.dto;

import com.musichead.capstone2022_backend.domain.member.Member;
import com.musichead.capstone2022_backend.domain.post.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LikeOutputDto {
    private Member member;
    private Post post;
    private Long likeCount;
}
