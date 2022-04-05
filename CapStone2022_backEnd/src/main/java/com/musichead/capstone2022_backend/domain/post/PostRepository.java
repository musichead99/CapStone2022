package com.musichead.capstone2022_backend.domain.post;

import com.musichead.capstone2022_backend.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByMemberIn(List<Member> subscribers);
}
