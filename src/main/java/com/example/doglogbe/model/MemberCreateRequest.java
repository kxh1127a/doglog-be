package com.example.doglogbe.model;

import com.example.doglogbe.type.MemberRole;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class MemberCreateRequest {
    private String name;
    private String userName;
    private String password;
    private String phone;
    private String email;
}
