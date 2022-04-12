package com.musichead.capstone2022_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class AudioController {

    @PostMapping(value = "/audio")
    public String postAudio(@RequestPart MultipartFile multipartFile) {
        return multipartFile.getOriginalFilename();
    }

    @GetMapping(value = "/audio")
    public String getAudio() {
        return "test";
    }
}
