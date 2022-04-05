package com.musichead.capstone2022_backend.domain.subscribe;

import com.musichead.capstone2022_backend.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {
    @Query(value = "select sub.toMember from Subscribe sub where sub.fromMember = ?1", nativeQuery = false)
    List<Member> findToMemberByFromMember(Member fromMember);
}
