package com.example.doglogbe.model;

public record JoinRequest(
        String name,
        String userName,
        String password,
        String rePassword,
        String phone,
        String email
) {
}
