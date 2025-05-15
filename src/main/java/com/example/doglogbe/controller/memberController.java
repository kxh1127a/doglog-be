package com.example.doglogbe.controller;

import com.example.doglogbe.entity.Member;
import com.example.doglogbe.model.MemberCreateRequest;
import com.example.doglogbe.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class memberController {
    private final MemberService memberService;

    @PostMapping("/new")
    public String setMember(@RequestBody MemberCreateRequest request) {
        memberService.setMember(request);
        return "success";
    }
}
