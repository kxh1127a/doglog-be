package com.example.doglogbe.entity;

import com.example.doglogbe.enums.MemberRole;
import com.example.doglogbe.model.AuthJoinRequest;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberRole role = MemberRole.USER;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 20, unique = true)
    private String phone;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = true)
    private LocalDateTime lastLoginAt;

    @Column(nullable = false)
    private Boolean isEnabled = true;

    @Column(nullable = true)
    private String statusReason;

    @Column(nullable = true)
    private Integer totalLike = 0;

    @Column(nullable = true)
    private Integer totalQuestion = 0;

    @JsonBackReference
    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Pet> pets = new ArrayList<>();

    @JsonBackReference
    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<TipLike> tips = new ArrayList<>();

    @JsonBackReference
    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Question> questions = new ArrayList<>();


    @Builder
    public Member(AuthJoinRequest authJoinRequest, String encodePassword, MemberRole role) {
        this.role = role;
        this.name = authJoinRequest.name();
        this.userName = authJoinRequest.userName();
        this.password = encodePassword;
        this.phone = authJoinRequest.phone();
        this.email = authJoinRequest.email();
        this.createdAt = LocalDateTime.now();
        this.lastLoginAt = LocalDateTime.now();
        this.isEnabled = true;
    }
}
