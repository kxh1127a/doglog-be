package com.example.doglogbe.service;

import com.example.doglogbe.entity.Member;
import com.example.doglogbe.entity.Pet;
import com.example.doglogbe.model.MemberCreateRequest;
import com.example.doglogbe.model.MemberItem;
import com.example.doglogbe.repository.MemberRepository;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    // 멤버 등록 기능 구체화 필요 (jwt, 중복방지, 정규식 등등)
    public void setMember(MemberCreateRequest memberCreateRequest) {
        memberRepository.save(new Member.Builder(memberCreateRequest).build());
    }

    public Page<MemberItem> getMembers(int page, int size, String filter) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        Page<Member> target;
        switch (filter) {
            case "normal" -> target = memberRepository.findByIsEnabled(true, pageable);
            case "withdrawn" -> target = memberRepository.findByIsEnabled(false, pageable);
            default -> target = memberRepository.findAll(pageable);
        }

        List<MemberItem> result = target.getContent().stream()
                .map(item -> new MemberItem.Builder(item).build())
                .collect(Collectors.toList());



        return new PageImpl<>(result, pageable, target.getTotalElements());
    }
}
