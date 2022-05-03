package com.musichead.capstone2022_backend.domain.post;

import com.musichead.capstone2022_backend.domain.board.Board;
import com.musichead.capstone2022_backend.domain.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "select p from Post p where p.member in (select sub.toMember from Subscribe sub where sub.fromMember.id = :memberId)")
    List<Post> findSubscriberPostsByMemberId(@Param("memberId")Long id);

    Page<Post> findByBoard(Board board, Pageable pageable);
}
