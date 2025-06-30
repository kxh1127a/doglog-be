package com.example.doglogbe.controller;

import com.example.doglogbe.exception.CInvalidLoginProviderException;
import com.example.doglogbe.model.AuthJoinRequest;
import com.example.doglogbe.model.AuthLoginRequest;
import com.example.doglogbe.model.AuthLoginResponse;
import com.example.doglogbe.model.AuthMeResponse;
import com.example.doglogbe.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "유저 생성")
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

    @PostMapping("/login")
    public AuthLoginResponse doLogin(@RequestBody AuthLoginRequest authLoginRequest) {
        return authService.doLogin(authLoginRequest);
    }

    @GetMapping("/me")
    public AuthMeResponse getCurrentUser(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new CInvalidLoginProviderException();
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof UserDetails userDetails) {
            String authority = userDetails.getAuthorities().stream()
                    .findFirst()
                    .map(GrantedAuthority::getAuthority)
                    .orElse("");
            return new AuthMeResponse(userDetails.getUsername(), authority);
        }
    }



}
