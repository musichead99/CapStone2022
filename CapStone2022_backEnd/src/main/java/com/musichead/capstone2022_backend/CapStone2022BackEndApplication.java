package com.musichead.capstone2022_backend;

import com.musichead.capstone2022_backend.domain.board.Board;
import com.musichead.capstone2022_backend.domain.board.BoardRepository;
import com.musichead.capstone2022_backend.domain.user.Member;
import com.musichead.capstone2022_backend.domain.user.MemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CapStone2022BackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(CapStone2022BackEndApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(MemberRepository memberRepository, BoardRepository boardRepository) throws Exception {
        return (args) -> {
            boardRepository.save(new Board().builder().name("전체").build());
            memberRepository.save(new Member().builder().email("musichead99@naver.com").name("정성구").picture(null).build());

        };
    }
}
