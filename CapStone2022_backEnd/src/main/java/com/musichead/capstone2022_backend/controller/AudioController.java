package com.musichead.capstone2022_backend.controller;

import com.musichead.capstone2022_backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ResourceUtils;
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

    @PostMapping(value = "/audio")
    public String postAudio(@RequestPart("audio") MultipartFile multipartFile, @RequestParam(value = "post")Long postId) throws IOException {
        return postService.update(postId, multipartFile);
    }

    @GetMapping(value = "/audio/{name}")
    public void getAudio(HttpServletResponse response, @PathVariable String name) throws IOException {
        String filePath = System.getProperty("user.dir") + File.separator + "audio" + File.separator;
        Path path = Paths.get(filePath + name);

        String mimeType = Files.probeContentType(path);
        response.setContentType(mimeType);
        response.setContentLength((int) Files.size(path));
        response.setHeader("accept-ranges", "bytes");
        Files.copy(path, response.getOutputStream());
        response.flushBuffer();
    }
}
