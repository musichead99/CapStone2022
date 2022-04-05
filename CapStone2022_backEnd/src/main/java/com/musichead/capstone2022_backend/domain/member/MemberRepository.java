package com.musichead.capstone2022_backend.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByIdIn(List<Long> subscriberId);
}
