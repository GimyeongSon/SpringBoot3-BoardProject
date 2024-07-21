package com.springbooot.boardproject.entity;

import jakarta.persistence.*;

@Entity
public class Member {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String email;

    @Column
    private String password;

    public Member(){}
    public Member(Long id, String email, String password){
        this.id = id;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
