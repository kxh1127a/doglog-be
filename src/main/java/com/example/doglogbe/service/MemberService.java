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

    public Page<MemberItem> getMembers(int page, int size, String filter) {
        // Pageable: 현재 페이지 정보 (페이지 번호, 크기, 정렬 등)를 담고 있는 객체
        // PageRequest.of(0, 10, Sort.by("name")) => 현재 페이지 정보 생성 (자동으로 SQL 쿼리 만들기 위한 힌트)
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        // findAll(Pageable pageable) Spring Data JPA 에서 제공하는 자동 페이징 기능 (힌트를 통해 SQL 쿼리 자동 생성함)
        // Page<>은 다음 정보를 포함
        // (전체 데이터 개수 (getTotalElements()), 전체 페이지 개수 (getTotalPages()), 현재 페이지 번호 (getNumber()),
        //  현재 페이지의 데이터 리스트 (getContent()) ← 이게 바로 실제 멤버 목록!)
        Page<Member> target;
        if(filter.equals("normal")) {
            target = memberRepository.findByIsEnabled(true, pageable);
        } else if(filter.equals("withdrawn")) {
            target = memberRepository.findByIsEnabled(false, pageable);
        } else {
            target = memberRepository.findAll(pageable);
        }

        List<MemberItem> result = new LinkedList<>();

        // target.getContent()로 현재 페이지에 해당하는 회원 리스트(List<Member>)만 추출 가능
        target.getContent().forEach((item)->{
            MemberItem addItem = new MemberItem();
            addItem.setName(item.getName());
            addItem.setUserName(item.getUserName());
            addItem.setEmail(item.getEmail());
            addItem.setPhone(item.getPhone());
            addItem.setCreatedAt(item.getCreatedAt().toLocalDate());
            addItem.setIsEnabled(item.getIsEnabled());


            // 펫 정보 (Entity에 @OneToMany 추가했음 => FK 역으로 가져오기 위해서)
            // @OneToMany(mappedBy = "member", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
            // private List<Pet> pets = new ArrayList<>();
            if(!item.getPets().isEmpty()) {
                Pet pet = item.getPets().get(0); // 대표 반려동물 1마리 선택
                addItem.setPetName(pet.getName());
                addItem.setPetProfileImageUrl(pet.getProfileImageUrl());

                // 날짜 포맷 명시하는 방법
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                addItem.setPetBirthDate(pet.getBirthDate().format(formatter));
            }

            // 마지막 로그인 정보 (LocalDate 에서 String '@@일 전' 형태로)
            if(item.getLastLoginAt() != null) {
                long days = ChronoUnit.DAYS.between(item.getLastLoginAt().toLocalDate(), LocalDate.now());
                addItem.setLastLoginInfo(days + " days ago");
            } else {
                addItem.setLastLoginInfo("기록 없음");
            }

            result.add(addItem);
        });

        //Spring Data 에서 제공하는 Page<T> 인터페이스의 기본 구현체
        //new PageImpl<>(데이터리스트, 페이지정보객체, 페이징이 되지 않은 전체 결과의 개수)
        return new PageImpl<>(result, pageable, target.getTotalElements());
    }
}
