package com.springbooot.boardproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springbooot.boardproject.entity.Member;

import java.util.ArrayList;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Override
    ArrayList<Member> findAll();
}
