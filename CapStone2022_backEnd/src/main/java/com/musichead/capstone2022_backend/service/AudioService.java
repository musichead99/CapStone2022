package com.musichead.capstone2022_backend.service;

import com.musichead.capstone2022_backend.domain.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RequiredArgsConstructor
@Service
public class AudioService {

    String path = System.getProperty("user.dir") + File.separator + "audio" + File.separator;
    private final PostRepository postRepository;

    public void sendAudio(HttpServletResponse response, Long postId) throws IOException {

        String name = postRepository.findRealAudioById(postId);
        if(name == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot find Audio from post " + postId);

        String filePath = System.getProperty("user.dir") + File.separator + "audio" + File.separator;
        Path path = Paths.get(filePath + name);

        String mimeType = Files.probeContentType(path);
        response.setContentType(mimeType);
        response.setContentLength((int) Files.size(path));
        response.setHeader("accept-ranges", "bytes");
        Files.copy(path, response.getOutputStream());
        response.flushBuffer();
    }

    public String deleteAudio(Long postId) throws IOException {
        String fileName = postRepository.findRealAudioById(postId);
        if(fileName == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot find Audio from post " + postId);

        File file = new File(path + fileName);
        if(file.exists()) {
            file.delete();
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "file " + fileName + " does not exist");
        }
        return fileName;
    }
}
