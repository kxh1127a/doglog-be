package com.example.doglogbe.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberCreateRequest {
    private String name;
    private String userName;
    private String password;
    private String phone;
    private String email;
}
