package com.example.doglogbe.model;

public record AuthJoinRequest(
        String name,
        String userName,
        String password,
        String rePassword,
        String phone,
        String email
) {
}
