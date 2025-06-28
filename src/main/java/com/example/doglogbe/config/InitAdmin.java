package com.example.doglogbe.config;

import com.example.doglogbe.entity.Member;
import com.example.doglogbe.enums.MemberRole;
import com.example.doglogbe.model.AuthJoinRequest;
import com.example.doglogbe.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class InitAdmin {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initAdmin() {
        if (!memberRepository.existsByUserName("admin")) {
            AuthJoinRequest initRequest = new AuthJoinRequest(
                    "관리자",
                    "admin",
                    "admin1234",
                    "admin1234",
                    "010-0000-0000",
                    "admin@example.com"
            );

            String encoded = passwordEncoder.encode("admin1234");

            Member admin = Member.builder()
                    .authJoinRequest(initRequest)
                    .encodePassword(encoded)
                    .role(MemberRole.ADMIN)
                    .build();

            memberRepository.save(admin);
        }
    }

}
