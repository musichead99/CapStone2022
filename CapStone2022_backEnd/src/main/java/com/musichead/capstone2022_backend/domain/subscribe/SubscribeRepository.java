package com.musichead.capstone2022_backend.domain.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {
    @Query(value = "select to_member from subscribe where from_member = :id", nativeQuery = true)
    List<Long> findToMemberIdByFromMemberId(@Param("id") Long id);
}
