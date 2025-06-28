package com.example.doglogbe.service;

import com.example.doglogbe.config.JwtUtil;
import com.example.doglogbe.entity.Member;
import com.example.doglogbe.enums.MemberRole;
import com.example.doglogbe.exception.*;
import com.example.doglogbe.model.JoinRequest;
import com.example.doglogbe.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public void doJoinAsUser(JoinRequest request) {
        createMember(request, MemberRole.USER);
    }

    public void doJoinAsAdmin(JoinRequest request) {
        createMember(request, MemberRole.ADMIN);
    }

    private void createMember(JoinRequest request, MemberRole role) {
        if (memberRepository.existsByUserName(request.userName())) {
            throw new CDuplicateUsernameException();
        }
        if (memberRepository.existsByEmail(request.email())) {
            throw new CDuplicateEmailException();
        }
        if (memberRepository.existsByPhone(request.phone())) {
            throw new CDuplicatePhoneException();
        }

        if (!request.password().equals(request.rePassword())) {
            throw new CInvalidPasswordException();
        }

        String encodedPassword = passwordEncoder.encode(request.password());

        Member member = Member.builder()
                .joinRequest(request)
                .encodePassword(encodedPassword)
                .role(role)
                .build();

        memberRepository.save(member);
    }
}
