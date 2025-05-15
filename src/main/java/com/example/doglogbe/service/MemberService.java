package com.example.doglogbe.service;

import com.example.doglogbe.entity.Member;
import com.example.doglogbe.model.MemberCreateRequest;
import com.example.doglogbe.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void setMember(MemberCreateRequest memberCreateRequest) {
        Member member = new Member();
        member.setName(memberCreateRequest.getName());
        member.setUserName(memberCreateRequest.getUserName());
        member.setPassword(memberCreateRequest.getPassword());
        member.setPhone(memberCreateRequest.getPhone());
        member.setEmail(memberCreateRequest.getEmail());
        member.setCreatedAt(LocalDateTime.now());
        member.setLastLoginAt(LocalDateTime.now());
        memberRepository.save(member);
    }
}
