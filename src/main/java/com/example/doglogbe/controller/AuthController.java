package com.example.doglogbe.controller;

import com.example.doglogbe.model.AuthJoinRequest;
import com.example.doglogbe.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
    public String userJoin(@RequestBody AuthJoinRequest authJoinRequest) {
        authService.doJoinAsUser(authJoinRequest);
        return "회원가입 완료";
    }

    @PostMapping("/admin/join")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "관리자 생성", security = @SecurityRequirement(name = "Authorization"))
    public String adminJoin(@RequestBody AuthJoinRequest authJoinRequest) {
        authService.doJoinAsAdmin(authJoinRequest);
        return "관리자 생성 완료";
    }


}
