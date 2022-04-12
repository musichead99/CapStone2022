package com.musichead.capstone2022_backend.domain.subscribe;

import com.musichead.capstone2022_backend.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {

    Optional<Subscribe> findByFromMemberAndToMember(Member fromMember, Member toMember);

    Long countByFromMember(Member fromMember);

    @Query( value = "select sub.toMember from Subscribe sub where sub.fromMember.id = :memberId")
    List<Member> findToMemberByMemberId(@Param("memberId")Long memberId);
}
