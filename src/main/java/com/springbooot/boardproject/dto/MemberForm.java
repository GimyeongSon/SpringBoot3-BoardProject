package com.springbooot.boardproject.dto;

import com.springbooot.boardproject.entity.Member;
import lombok.Data;

@Data
public class MemberForm {
    private String email;
    private String password;

    public Member toEntity(){
        return new Member(null, email, password);
    }
}
