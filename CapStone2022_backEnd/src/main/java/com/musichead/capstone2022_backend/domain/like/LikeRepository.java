package com.musichead.capstone2022_backend.domain.like;

import com.musichead.capstone2022_backend.domain.member.Member;
import com.musichead.capstone2022_backend.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Long countByPost(Post post);

    Optional<Like> findByPostAndMember(Post post, Member member);
}
