package com.example.doglogbe.service;

import com.example.doglogbe.entity.CareTip;
import com.example.doglogbe.entity.CareTipResponse;
import com.example.doglogbe.entity.Member;
import com.example.doglogbe.exception.CCareTipNotFoundException;
import com.example.doglogbe.model.CareTipCreateRequest;
import com.example.doglogbe.model.CareTipItem;
import com.example.doglogbe.model.CareTipSearchRequest;
import com.example.doglogbe.model.MemberItem;
import com.example.doglogbe.repository.CareTipRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CareTipService {

    private final CareTipRepository careTipRepository;

    @PersistenceContext
    private EntityManager entityManager;

    //care tip 생성하기(제목,내용,카테고리만 받습니다)
    public void setCareTip(CareTipCreateRequest careTipCreateRequest) {
        careTipRepository.save(new CareTip.Builder(careTipCreateRequest).build());
    }

    //care tip 리스트로 전체 목록 가져오기(컨텐츠는 빠져있습니다)
    public Page<CareTipItem> getCareTips(int page) {
        Pageable pageable = PageRequest.of(page, 10);

        Page<CareTip> careTips = careTipRepository.findAll(pageable);
        List<CareTipItem> careTipItems = new LinkedList<>();
        for( CareTip careTip: careTips ) {
            careTipItems.add(new CareTipItem.Builder(careTip).build());
        }
        return new PageImpl<>(careTipItems, pageable, careTips.getTotalElements());
    }

    //care tip 전체 목록 중 isEnabled가 true인 가져오기(isEnabled true)
    public Page<CareTipItem> getCareTipsIsEnabled(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<CareTip> careTips = careTipRepository.findAllByIsEnabled(true, pageable);
        List<CareTipItem> careTipItems = new LinkedList<>();
        for( CareTip careTip: careTips ) {
            careTipItems.add(new CareTipItem.Builder(careTip).build());
        }
        return new PageImpl<>(careTipItems, pageable, careTips.getTotalElements());
    }

    //care tip 단일 항목 가져오기
    public CareTipResponse getCareTip(long id){
        CareTip careTip = careTipRepository.findById(id).orElseThrow(CCareTipNotFoundException::new);
        return new CareTipResponse.Builder(careTip).build();
    }

    //care tip 추천 여부 변경
    public void putCareTipByRecommend(long id){
        CareTip careTip = careTipRepository.findById(id).orElseThrow(CCareTipNotFoundException::new);
        careTip.setRecommend(!careTip.getRecommend());
        careTipRepository.save(careTip);
    }

    //care tip 활성화 여부 변경
    public void putCareTipByEnabled(long id){
        CareTip careTip = careTipRepository.findById(id).orElseThrow(CCareTipNotFoundException::new);
        careTip.setIsEnabled(!careTip.getIsEnabled());
        careTipRepository.save(careTip);
    }

    // 케어팁 search 기능 구현
    public Page<CareTipItem> getCareTipBySearch(CareTipSearchRequest request) {

        // 1. 쿼리 빌더와 루트 객체 생성
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CareTip> cq = cb.createQuery(CareTip.class);
        Root<CareTip> root = cq.from(CareTip.class);

        // 2. 동적 where 조건을 담을 리스트 생성
        List<Predicate> predicates = new LinkedList<>();

        // 3. 조건 생성
        if (request.getTitle() != null && !request.getTitle().isEmpty()) {
            predicates.add(cb.like(root.get("title"), "%" + request.getTitle() + "%"));
        }
        if (request.getContent() != null && !request.getContent().isEmpty()) {
            predicates.add(cb.like(root.get("content"), "%" + request.getContent() + "%"));
        }

        predicates.add(cb.isTrue(root.get("isEnabled")));


        // 4. 조건 적용
        cq.where(cb.and(predicates.toArray(new Predicate[0])));


        // 5. 정렬 조건 설정
        if(request.getDirection().equals("desc")) {
            cq.orderBy(cb.desc(root.get(request.getSortBy())));
        } else {
            cq.orderBy(cb.asc(root.get(request.getSortBy())));
        }

        // 6. 쿼리 실행 준비
        TypedQuery<CareTip> query = entityManager.createQuery(cq);

        // 7. 페이징 처리
        int page = request.getPage();
        int size = request.getSize();
        query.setFirstResult(page * size);  // 몇 번째부터
        query.setMaxResults(size);          // 몇 개 가져올지

        List<CareTip> resultList = query.getResultList();
        List<CareTipItem> result = resultList.stream()
                .map(item -> new CareTipItem.Builder(item).build())
                .collect(Collectors.toList());

        // 8. 전체 개수 구하는 count 쿼리 작성
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<CareTip> countRoot = countQuery.from(CareTip.class);
        countQuery.select(cb.count(countRoot));

        // count 조건 동일하게 적용
        List<Predicate> countPredicates = new ArrayList<>();
        if (request.getTitle() != null && !request.getTitle().isEmpty()) {
            countPredicates.add(cb.like(countRoot.get("title"), "%" + request.getTitle() + "%"));
        }
        if (request.getContent() != null && !request.getContent().isEmpty()) {
            countPredicates.add(cb.like(countRoot.get("content"), "%" + request.getContent() + "%"));
        }

        countQuery.where(cb.and(countPredicates.toArray(new Predicate[0])));
        Long total = entityManager.createQuery(countQuery).getSingleResult();

        // 9. Page 객체로 리턴
        return new PageImpl<>(result, PageRequest.of(page, size), total);

    }





}
