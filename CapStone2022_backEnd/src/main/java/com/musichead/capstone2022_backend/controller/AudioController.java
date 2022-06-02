package com.musichead.capstone2022_backend.controller;

import com.musichead.capstone2022_backend.domain.post.PostRepository;
import com.musichead.capstone2022_backend.service.AudioService;
import com.musichead.capstone2022_backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RequiredArgsConstructor
@RestController
public class AudioController {

    private final PostService postService;
    private final AudioService audioService;

    @GetMapping(value = "/audio")
    public void getAudio(HttpServletResponse response, @RequestParam(value = "post")Long postId) throws IOException {
        audioService.sendAudio(response, postId);
    }

    @PutMapping(value = "/audio")
    public String putAudio(@RequestPart("audio") MultipartFile multipartFile, @RequestParam(value = "post")Long postId) {
        return postService.update(postId, multipartFile);
    }

    @DeleteMapping(value = "/audio")
    public String deleteAudio(@RequestParam(value = "post")Long postId) throws IOException {
        return audioService.deleteAudio(postId);
    }
}
