package com.example.doglogbe.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberSearchRequest {
    private String name;
    private String userName;
    private String email;
    private String phone;

    private String orderBy = "id";
    private int page = 0;
    private int size = 10;
}
