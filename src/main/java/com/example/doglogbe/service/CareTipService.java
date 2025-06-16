package com.example.doglogbe.service;

import com.example.doglogbe.entity.CareTip;
import com.example.doglogbe.entity.CareTipCategoryActive;
import com.example.doglogbe.entity.CareTipResponse;
import com.example.doglogbe.entity.Member;
import com.example.doglogbe.enums.CareTipCategory;
import com.example.doglogbe.exception.CCareTipNotFoundException;
import com.example.doglogbe.model.*;
import com.example.doglogbe.model.result.ListResult;
import com.example.doglogbe.repository.CareTipCategoryActiveRepository;
import com.example.doglogbe.repository.CareTipRepository;
import com.example.doglogbe.repository.MemberRepository;
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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CareTipService {

    private final CareTipRepository careTipRepository;
    private final CareTipCategoryActiveRepository careTipCategoryActiveRepository;

    @PersistenceContext
    private EntityManager entityManager;

    //care tip 생성하기(제목,내용,카테고리만 받습니다) + 이미지 업로드 추가했습니다
    @Transactional
    public CareTip createCareTip(CareTipCreateRequest careTipCreateRequest, MultipartFile image) {
        String imgUrl = null;
        if (image != null && !image.isEmpty()) {
            imgUrl = saveImage(image);
        }

        CareTip careTip = new CareTip.Builder(careTipCreateRequest)
                .imgUrl(imgUrl)
                .build();

        return careTipRepository.save(careTip);
    }

    //care tip 리스트로 전체 목록 가져오기(컨텐츠는 빠져있습니다)
    public Page<CareTipItem> getCareTips(
            int page,
            int size,
            String sortBy,
            String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromString(direction), sortBy);
        return careTipRepository.findAll(pageable)
                .map(CareTipItem::from);
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
    public Page<CareTipItem> searchCareTips(CareTipSearchRequest searchRequest) {
        Pageable pageable = PageRequest.of(
                searchRequest.getPage(),
                searchRequest.getSize(),
                Sort.Direction.fromString(searchRequest.getDirection()),
                searchRequest.getSortBy()
        );

        if (searchRequest.getCategory() != null) {
            return careTipRepository.findByCareTipCategory(searchRequest.getCategory(), pageable)
                    .map(CareTipItem::from);
        }

        return careTipRepository.findAll(pageable)
                .map(CareTipItem::from);
    }

    public List<ActiveCareTipCategory> getCareTipCategoryActive() {
        List<CareTipCategoryActive> careTipCategoryActives = careTipCategoryActiveRepository.findAll();
        List<ActiveCareTipCategory> activeCareTipCategories = new LinkedList<>();
        for(CareTipCategoryActive careTipCategoryActive: careTipCategoryActives) {
            activeCareTipCategories.add(new ActiveCareTipCategory.Builder(careTipCategoryActive).build());
        }
        return activeCareTipCategories;
    }

    // 카테고리별 케어팁 목록 조회
    public Page<CareTipItem> getCareTipsByCategory(String category, int page) {
        final int PAGE_SIZE = 10;
        
        // 1. 쿼리 빌더와 루트 객체 생성
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CareTip> cq = cb.createQuery(CareTip.class);
        Root<CareTip> root = cq.from(CareTip.class);

        // 2. 동적 where 조건을 담을 리스트 생성
        List<Predicate> predicates = new LinkedList<>();

        // 3. 조건 생성
        if (category != null && !category.isEmpty()) {
            try {
                CareTipCategory categoryEnum = CareTipCategory.valueOf(category);
                predicates.add(cb.equal(root.get("careTipCategory"), categoryEnum));
            } catch (IllegalArgumentException e) {
                // 잘못된 카테고리명이 들어온 경우 빈 결과 반환
                return new PageImpl<>(new ArrayList<>(), PageRequest.of(page, PAGE_SIZE), 0L);
            }
        }
        predicates.add(cb.isTrue(root.get("isEnabled")));

        // 4. 조건 적용
        cq.where(cb.and(predicates.toArray(new Predicate[0])));

        // 5. 정렬 조건 설정 (최신순)
        cq.orderBy(cb.desc(root.get("editDate")));

        // 6. 쿼리 실행 준비
        TypedQuery<CareTip> query = entityManager.createQuery(cq);

        // 7. 페이징 처리
        query.setFirstResult(page * PAGE_SIZE);
        query.setMaxResults(PAGE_SIZE);

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
        if (category != null && !category.isEmpty()) {
            try {
                CareTipCategory categoryEnum = CareTipCategory.valueOf(category);
                countPredicates.add(cb.equal(countRoot.get("careTipCategory"), categoryEnum));
            } catch (IllegalArgumentException e) {
                // 잘못된 카테고리명이 들어온 경우 0 반환
                return new PageImpl<>(new ArrayList<>(), PageRequest.of(page, PAGE_SIZE), 0L);
            }
        }
        countPredicates.add(cb.isTrue(countRoot.get("isEnabled")));

        countQuery.where(cb.and(countPredicates.toArray(new Predicate[0])));
        Long total = entityManager.createQuery(countQuery).getSingleResult();

        // 9. Page 객체로 리턴
        return new PageImpl<>(result, PageRequest.of(page, PAGE_SIZE), total);
    }

    private String saveImage(MultipartFile image) {
        try {
            // 파일 확장자 검사
            String originalFilename = image.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
            if (!extension.matches("jpg|jpeg|png|gif")) {
                throw new IllegalArgumentException("지원하지 않는 이미지 형식입니다.");
            }

            // 파일명 생성 (현재시간 + 랜덤문자열)
            String fileName = System.currentTimeMillis() + "_" + 
                java.util.UUID.randomUUID().toString().substring(0, 8) + "." + extension;

            // 저장할 경로 생성
            String uploadDir = "uploads/caretip";
            java.io.File dir = new java.io.File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // 파일 저장
            java.io.File destFile = new java.io.File(dir, fileName);
            image.transferTo(destFile);

            // DB에 저장할 URL 경로 생성
            return "/uploads/caretip/" + fileName;
        } catch (Exception e) {
            throw new RuntimeException("이미지 업로드에 실패했습니다.", e);
        }
    }

}
