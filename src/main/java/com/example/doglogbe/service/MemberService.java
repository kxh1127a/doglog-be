package com.example.doglogbe.service;

import com.example.doglogbe.entity.Member;
import com.example.doglogbe.entity.Pet;
import com.example.doglogbe.exception.CUserNotFoundException;
import com.example.doglogbe.model.MemberCreateRequest;
import com.example.doglogbe.model.MemberItem;
import com.example.doglogbe.model.MemberSearchRequest;
import com.example.doglogbe.repository.MemberRepository;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hibernate.Hibernate.map;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    @PersistenceContext
    private EntityManager entityManager;

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

    public Page<MemberItem> getMembersBySearch(MemberSearchRequest request) {

        // 1. 쿼리 빌더와 루트 객체 생성
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Member> cq = cb.createQuery(Member.class);
        Root<Member> root = cq.from(Member.class);

        // 2. 동적 where 조건을 담을 리스트 생성
        List<Predicate> predicates = new ArrayList<>();

        // 3. 조건 생성
        if (request.getName() != null) {
            predicates.add(cb.like(root.get("name"), "%" + request.getName() + "%"));
        }
        if (request.getUserName() != null) {
            predicates.add(cb.equal(root.get("userName"), request.getUserName()));
        }
        if (request.getEmail() != null) {
            predicates.add(cb.like(root.get("email"), "%" + request.getEmail() + "%"));
        }
        if (request.getPhone() != null) {
            predicates.add(cb.like(root.get("phone"), "%" + request.getPhone() + "%"));
        }

        // 4. 조건 적용
        cq.where(cb.and(predicates.toArray(new Predicate[0])));

        // 5. 정렬 조건 설정
        cq.orderBy(cb.desc(root.get(request.getOrderBy())));

        // 6. 쿼리 실행 준비
        TypedQuery<Member> query = entityManager.createQuery(cq);

        // 7. 페이징 처리
        int page = request.getPage();
        int size = request.getSize();
        query.setFirstResult(page * size);  // 몇 번째부터
        query.setMaxResults(size);          // 몇 개 가져올지

        List<Member> resultList = query.getResultList();
        List<MemberItem> result = resultList.stream()
                .map(item->new MemberItem.Builder(item).build())
                .collect(Collectors.toList());

        // 8. 전체 개수 구하는 count 쿼리 작성
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Member> countRoot = countQuery.from(Member.class);
        countQuery.select(cb.count(countRoot));

        // count 조건 동일하게 적용
        List<Predicate> countPredicates = new ArrayList<>();
        if (request.getName() != null) {
            countPredicates.add(cb.like(countRoot.get("name"), "%" + request.getName() + "%"));
        }
        if (request.getUserName() != null) {
            countPredicates.add(cb.equal(countRoot.get("userName"), request.getUserName()));
        }
        if (request.getEmail() != null) {
            countPredicates.add(cb.like(countRoot.get("email"), "%" + request.getEmail() + "%"));
        }
        if (request.getPhone() != null) {
            countPredicates.add(cb.like(countRoot.get("phone"), "%" + request.getPhone() + "%"));
        }

        countQuery.where(cb.and(countPredicates.toArray(new Predicate[0])));
        Long total = entityManager.createQuery(countQuery).getSingleResult();

        // 9. Page 객체로 리턴
        return new PageImpl<>(result, PageRequest.of(page, size), total);
    }
}
