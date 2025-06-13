package com.example.doglogbe.service;

import com.example.doglogbe.entity.Member;
import com.example.doglogbe.entity.Pet;
import com.example.doglogbe.entity.PetBreed;
import com.example.doglogbe.exception.CUserNotFoundException;
import com.example.doglogbe.model.MemberItem;
import com.example.doglogbe.model.MemberResponse;
import com.example.doglogbe.model.PetItem;
import com.example.doglogbe.model.PetSearchRequest;
import com.example.doglogbe.repository.PetRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PetService {
    private final PetRepository petRepository;

    @PersistenceContext
    private final EntityManager entityManager;

    public Page<PetItem> getPets(int size, int page) {
        Pageable pageable = PageRequest.of(size, page);
        Page<Pet> target = petRepository.findAll(pageable);

        List<PetItem> result = target.stream()
                .map(item -> new PetItem.Builder(item).build())
                .collect(Collectors.toList());

        return new PageImpl<>(result, pageable, target.getTotalElements());

    }

    public Page<PetItem> getPetsBySearch(PetSearchRequest request) {

        // 1. 쿼리 빌더와 루트 객체 생성
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pet> cq = cb.createQuery(Pet.class);
        Root<Pet> root = cq.from(Pet.class);

        // 2. 동적 where 조건을 담을 리스트 생성
        List<Predicate> predicates = new ArrayList<>();

        // 3. 조건 생성
        if (request.getByName() != null && !request.getByName().isEmpty()) {
            predicates.add(cb.like(root.get("name"), "%" + request.getByName() + "%"));
        }

        // 🟡 PetBreed 조인
        Join<Pet, PetBreed> breedJoin = root.join("petBreed", JoinType.INNER);
        // 🟢 PetBreed.breedName을 조건으로 검색
        if (request.getByBreed() != null && !request.getByBreed().isEmpty()) {
            predicates.add(cb.equal(breedJoin.get("breedName"), request.getByBreed()));
        }

        // 4. 조건 적용
        cq.where(cb.and(predicates.toArray(new Predicate[0])));

        // 6. 쿼리 실행 준비
        TypedQuery<Pet> query = entityManager.createQuery(cq);

        // 7. 페이징 처리
        int page = request.getPage();
        int size = request.getSize();
        query.setFirstResult(page * size);  // 몇 번째부터
        query.setMaxResults(size);          // 몇 개 가져올지

        List<Pet> resultList = query.getResultList();
        List<PetItem> result = resultList.stream()
                .map(item -> new PetItem.Builder(item).build())
                .collect(Collectors.toList());

        // 8. 전체 개수 구하는 count 쿼리 작성
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Pet> countRoot = countQuery.from(Pet.class);
        countQuery.select(cb.count(countRoot));

        // count 조건 동일하게 적용
        List<Predicate> countPredicates = new ArrayList<>();
        if (request.getByName() != null && !request.getByName().isEmpty()) {
            countPredicates.add(cb.like(countRoot.get("name"), "%" + request.getByName() + "%"));
        }

        // 👉 countRoot 기준으로 petBreed 조인
        Join<Pet, PetBreed> countBreedJoin = countRoot.join("petBreed", JoinType.INNER);

        if (request.getByBreed() != null && !request.getByBreed().isEmpty()) {
            countPredicates.add(cb.equal(countBreedJoin.get("breedName"), request.getByBreed()));
        }

        countQuery.where(cb.and(countPredicates.toArray(new Predicate[0])));
        Long total = entityManager.createQuery(countQuery).getSingleResult();

        // 9. Page 객체로 리턴
        return new PageImpl<>(result, PageRequest.of(page, size), total);
    }


}
