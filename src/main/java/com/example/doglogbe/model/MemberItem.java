package com.example.doglogbe.model;

import com.example.doglogbe.type.MemberRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class MemberItem {
    private String name;
    private String userName;
    private String email;
    private String phone;
    private String petName;
    private String petBirthDate;
    private LocalDate createdAt;
    private String lastLoginInfo;
    private Boolean isEnabled;
    private String petProfileImageUrl;
}
