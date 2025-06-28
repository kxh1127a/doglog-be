package com.example.doglogbe.model;

public record AuthLoginRequest(
        String username,
        String password
) {
}
