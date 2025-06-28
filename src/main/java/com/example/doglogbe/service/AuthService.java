package com.example.doglogbe.service;

import com.example.doglogbe.config.JwtUtil;
import com.example.doglogbe.entity.Member;
import com.example.doglogbe.enums.MemberRole;
import com.example.doglogbe.exception.*;
import com.example.doglogbe.lib.CommonCheck;
import com.example.doglogbe.model.AuthJoinRequest;
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

    public void doJoinAsUser(AuthJoinRequest request) {
        validateJoinRequest(request);
        registerMember(request, MemberRole.USER);
    }

    public void doJoinAsAdmin(AuthJoinRequest request) {
        validateJoinRequest(request);
        registerMember(request, MemberRole.ADMIN);
    }

    // 회원 등록
    private void registerMember(AuthJoinRequest request, MemberRole role) {
        String encodedPassword = passwordEncoder.encode(request.password());

        Member member = Member.builder()
                .authJoinRequest(request)
                .encodePassword(encodedPassword)
                .role(role)
                .build();

        memberRepository.save(member);
    }

    // 회원가입 유효성 검사
    private void validateJoinRequest(AuthJoinRequest request) {
        validateDuplicate(request);
        validatePassword(request);
        validateFormat(request);
    }

    private void validateDuplicate(AuthJoinRequest request) {
        if (memberRepository.existsByUserName(request.userName())) {
            throw new CDuplicateUsernameException();
        }
        if (memberRepository.existsByEmail(request.email())) {
            throw new CDuplicateEmailException();
        }
        if (memberRepository.existsByPhone(request.phone())) {
            throw new CDuplicatePhoneException();
        }
    }

    private void validatePassword(AuthJoinRequest request) {
        if (!request.password().equals(request.rePassword())) {
            throw new CInvalidPasswordException();
        }
        if (!CommonCheck.checkPassword(request.password())) {
            throw new CInvalidPasswordFormatException();
        }
    }

    private void validateFormat(AuthJoinRequest request) {
        if (!CommonCheck.checkUsername(request.userName())) {
            throw new CInvalidUsernameFormatException();
        }
        if (!CommonCheck.checkEmail(request.email())) {
            throw new CInvalidEmailFormatException();
        }
        if (!CommonCheck.checkPhone(request.phone())) {
            throw new CInvalidPhoneFormatException();
        }
    }
}
