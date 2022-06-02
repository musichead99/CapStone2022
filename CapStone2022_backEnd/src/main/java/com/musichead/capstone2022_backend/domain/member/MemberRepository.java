package com.musichead.capstone2022_backend.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByIdIn(List<Long> subscriberId);

    Optional<Member> findByEmail(String email);
}
