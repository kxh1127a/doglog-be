package com.example.doglogbe.service;

import com.example.doglogbe.entity.Member;
import com.example.doglogbe.entity.Pet;
import com.example.doglogbe.model.MemberCreateRequest;
import com.example.doglogbe.model.MemberItem;
import com.example.doglogbe.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;

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

    public Page<MemberItem> getMembers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        // 페이징 적용
        Page<Member> target = memberRepository.findAll(pageable);

        List<MemberItem> result = new LinkedList<>();

        target.getContent().forEach((item)->{
            MemberItem addItem = new MemberItem();
            addItem.setName(item.getName());
            addItem.setUserName(item.getUserName());
            addItem.setEmail(item.getEmail());
            addItem.setPhone(item.getPhone());
            addItem.setCreatedAt(item.getCreatedAt().toLocalDate());
            addItem.setIsEnabled(item.getIsEnabled());



            // 펫 정보 (대표 반려동물 1마리 선택) (Entity에 @OneToMany 설정했음 FK 역으로 가져오기)
            if(!item.getPets().isEmpty()) {
                Pet pet = item.getPets().get(0);
                addItem.setPetName(pet.getName());
                addItem.setPetProfileImageUrl(pet.getProfileImageUrl());

                // 날짜 포맷 명시하는 방법
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                addItem.setPetBirthDate(pet.getBirthDate().format(formatter));


            }

            // 마지막 로그인 정보 ( '2일 전' 형태로)
            if(item.getLastLoginAt() != null) {
                long days = ChronoUnit.DAYS.between(item.getLastLoginAt().toLocalDate(), LocalDate.now());
                addItem.setLastLoginInfo(days + " days ago");
            } else {
                addItem.setLastLoginInfo("기록 없음");
            }

            result.add(addItem);
        });

        return new PageImpl<>(result, pageable, target.getTotalElements());
    }

}
