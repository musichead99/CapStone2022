package com.musichead.capstone2022_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberDto {
    private String email;
    private String name;
    private String picture;
}
