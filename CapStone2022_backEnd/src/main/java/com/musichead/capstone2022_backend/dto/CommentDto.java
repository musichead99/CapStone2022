package com.musichead.capstone2022_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentDto {

    private Long postId;
    private Long memberId;
    private String content;
}
