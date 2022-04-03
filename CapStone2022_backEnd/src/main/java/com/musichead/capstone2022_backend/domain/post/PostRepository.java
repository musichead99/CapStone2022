package com.musichead.capstone2022_backend.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    public List<Post> findByMember_id(Long member_id);
}
