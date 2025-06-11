package com.example.doglogbe.service;

import com.example.doglogbe.entity.Member;
import com.example.doglogbe.entity.TipLike;
import com.example.doglogbe.exception.CUserNotFoundException;
import com.example.doglogbe.model.MemberCreateRequest;
import com.example.doglogbe.model.MemberItem;
import com.example.doglogbe.model.MemberResponse;
import com.example.doglogbe.model.MemberSearchRequest;
import com.example.doglogbe.repository.MemberRepository;
import com.example.doglogbe.repository.QuestionRepository;
import com.example.doglogbe.repository.TipLikeRepository;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final TipLikeRepository tipLikeRepository;
    private final QuestionRepository questionRepository;

    @PersistenceContext
    private EntityManager entityManager;

    // 멤버 등록 기능 구체화 필요 (jwt, 중복방지, 정규식 등등)
    public void setMember(MemberCreateRequest memberCreateRequest) {
        memberRepository.save(new Member.Builder(memberCreateRequest).build());
    }

    public Page<MemberItem> getMembers(int page, int size, String sortBy, String direction, String filter) {
        Sort sort = direction.equals("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

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
        if (request.getName() != null && !request.getName().isEmpty()) {
            predicates.add(cb.like(root.get("name"), "%" + request.getName() + "%"));
        }
        if (request.getUserName() != null && !request.getUserName().isEmpty()) {
            predicates.add(cb.equal(root.get("userName"), request.getUserName()));
        }
        if (request.getEmail() != null && !request.getEmail().isEmpty()) {
            predicates.add(cb.like(root.get("email"), "%" + request.getEmail() + "%"));
        }
        if (request.getPhone() != null && !request.getPhone().isEmpty()) {
            predicates.add(cb.like(root.get("phone"), "%" + request.getPhone() + "%"));
        }

        // filter 조건 추가
        if (request.getFilter() != null) {
            switch (request.getFilter()) {
                case "normal":
                    predicates.add(cb.isTrue(root.get("isEnabled")));
                    break;
                case "withdrawn":
                    predicates.add(cb.isFalse(root.get("isEnabled")));
                    break;
                case "all":
                default:
                    // 아무 조건도 추가하지 않음
                    break;
            }
        }

        // 4. 조건 적용
        cq.where(cb.and(predicates.toArray(new Predicate[0])));

        // 5. 정렬 조건 설정
        if(request.getDirection().equals("desc")) {
            cq.orderBy(cb.desc(root.get(request.getSortBy())));
        } else {
            cq.orderBy(cb.asc(root.get(request.getSortBy())));
        }

        // 6. 쿼리 실행 준비
        TypedQuery<Member> query = entityManager.createQuery(cq);

        // 7. 페이징 처리
        int page = request.getPage();
        int size = request.getSize();
        query.setFirstResult(page * size);  // 몇 번째부터
        query.setMaxResults(size);          // 몇 개 가져올지

        List<Member> resultList = query.getResultList();
        List<MemberItem> result = resultList.stream()
                .map(item -> new MemberItem.Builder(item).build())
                .collect(Collectors.toList());

        // 8. 전체 개수 구하는 count 쿼리 작성
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Member> countRoot = countQuery.from(Member.class);
        countQuery.select(cb.count(countRoot));

        // count 조건 동일하게 적용
        List<Predicate> countPredicates = new ArrayList<>();
        if (request.getName() != null && !request.getName().isEmpty()) {
            countPredicates.add(cb.like(countRoot.get("name"), "%" + request.getName() + "%"));
        }
        if (request.getUserName() != null && !request.getUserName().isEmpty()) {
            countPredicates.add(cb.equal(countRoot.get("userName"), request.getUserName()));
        }
        if (request.getEmail() != null && !request.getEmail().isEmpty()) {
            countPredicates.add(cb.like(countRoot.get("email"), "%" + request.getEmail() + "%"));
        }
        if (request.getPhone() != null && !request.getPhone().isEmpty()) {
            countPredicates.add(cb.like(countRoot.get("phone"), "%" + request.getPhone() + "%"));
        }

        if (request.getFilter() != null) {
            switch (request.getFilter()) {
                case "normal":
                    countPredicates.add(cb.isTrue(countRoot.get("isEnabled")));
                    break;
                case "withdrawn":
                    countPredicates.add(cb.isFalse(countRoot.get("isEnabled")));
                    break;
                case "all":
                default:
                    break;
            }
        }

        countQuery.where(cb.and(countPredicates.toArray(new Predicate[0])));
        Long total = entityManager.createQuery(countQuery).getSingleResult();

        // 9. Page 객체로 리턴
        return new PageImpl<>(result, PageRequest.of(page, size), total);
    }

    public MemberResponse getMember(long id) {
        Member target = memberRepository.findById(id).orElseThrow(CUserNotFoundException::new);
        long countTipLike = tipLikeRepository.countAllByMemberId(id);
        long countQuestion = questionRepository.countAllByMemberId(id);

        MemberResponse response = new MemberResponse.Builder(target, countTipLike, countQuestion).build();
        return response;
    }
}
