package com.springbooot.boardproject.dto;

import com.springbooot.boardproject.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class MemberForm {
    private Long id;
    private String email;
    private String password;

    public Member toEntity(){
        return new Member(id, email, password);
    }
}
