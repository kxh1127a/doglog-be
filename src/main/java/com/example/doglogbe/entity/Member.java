package com.example.doglogbe.entity;

import com.example.doglogbe.enums.MemberRole;
import com.example.doglogbe.interfaces.CommonModelBuilder;
import com.example.doglogbe.model.MemberCreateRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

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

    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Pet> pets = new ArrayList<>();

    private Member(Builder builder) {
        this.name = builder.name;
        this.userName = builder.userName;
        this.password = builder.password;
        this.phone = builder.phone;
        this.email = builder.email;
        this.createdAt = builder.createdAt;
        this.lastLoginAt = builder.lastLoginAt;
    }

    public static class Builder implements CommonModelBuilder<Member> {
        private final String name;
        private final String userName;
        private final String password;
        private final String phone;
        private final String email;
        private final LocalDateTime createdAt;
        private final LocalDateTime lastLoginAt;

        public Builder (MemberCreateRequest request) {
            this.name = request.getName();
            this.userName = request.getUserName();
            this.password = request.getPassword();
            this.phone = request.getPhone();
            this.email = request.getEmail();
            this.createdAt = LocalDateTime.now();
            this.lastLoginAt = LocalDateTime.now();
        }

        @Override
        public Member build() {
            return new Member(this);
        }
    }
}
