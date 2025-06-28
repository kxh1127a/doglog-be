package com.example.doglogbe.controller;

import com.example.doglogbe.model.JoinRequest;
import com.example.doglogbe.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/join")
    public String userJoin(@RequestBody JoinRequest joinRequest) {
        authService.doJoinAsUser(joinRequest);
        return "회원가입 완료";
    }

    @PostMapping("/admin/join")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminJoin(@RequestBody JoinRequest joinRequest) {
        authService.doJoinAsAdmin(joinRequest);
        return "관리자 생성 완료";
    }


}
