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
    private int page;
    private int size;

    // 검색어 옵션 및 검색어
    private String name;
    private String userName;
    private String email;
    private String phone;


    private String sortBy;
    private String direction;
    private String filter;

}
