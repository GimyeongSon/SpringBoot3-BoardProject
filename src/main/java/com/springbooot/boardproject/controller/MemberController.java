package com.springbooot.boardproject.controller;

import com.springbooot.boardproject.dto.MemberForm;
import com.springbooot.boardproject.entity.Member;
import com.springbooot.boardproject.repository.MemberRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Log4j2
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/signup")
    public String newMemberForm(){
        return "members/new";
    }

    @PostMapping("/join")
    public String createMember(MemberForm memberForm){
        Member member = memberForm.toEntity();
        log.info(member.toString());
        Member saved = memberRepository.save(member);
        log.info(saved);
        return "";
    }
}
