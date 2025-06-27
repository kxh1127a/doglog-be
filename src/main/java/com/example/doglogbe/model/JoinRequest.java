package com.example.doglogbe.model;

public record JoinRequest(
        String name,
        String userName,
        String phone,
        String email
) {
}
