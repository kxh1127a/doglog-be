package com.example.doglogbe.controller;

import com.example.doglogbe.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping('auth-test')
public class AuthTestController {
    private final AuthService authService;

    @PostMapping("/join")
    public String doJoin(@RequestBody JoinRequest joinRequest) {
        authService.doJoin(joinRequest);
        return "complete join";
    }


}
