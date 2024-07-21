package com.springbooot.boardproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springbooot.boardproject.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
