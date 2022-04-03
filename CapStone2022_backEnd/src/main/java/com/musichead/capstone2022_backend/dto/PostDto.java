package com.musichead.capstone2022_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class PostDto {

    private String title;
    private String content;
    private Long memberId;
    private Long boardId;
}
