package com.example.doglogbe.model;

public record AuthMeResponse (
        String username,
        String authorities
) {
}