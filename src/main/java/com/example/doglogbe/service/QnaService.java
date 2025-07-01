package com.example.doglogbe.service;

import com.example.doglogbe.entity.*;
import com.example.doglogbe.exception.CQuestionNotFoundException;
import com.example.doglogbe.exception.CUserNotFoundException;
import com.example.doglogbe.model.*;
import com.example.doglogbe.repository.AnswerRepository;
import com.example.doglogbe.repository.QuestionRepository;
import io.swagger.v3.oas.models.links.Link;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QnaService {
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public Page<QnaItem> getQnaList(QnaSearchRequest request) {
        // 1. 쿼리 빌더와 루트 객체 생성
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Question> cq = cb.createQuery(Question.class);
        Root<Question> root = cq.from(Question.class);

        // 2. 동적 where 조건을 담을 리스트 생성
        List<Predicate> predicates = new ArrayList<>();

        // 3. 조건 생성
        if(request.getByTitle() != null && !request.getByTitle().isEmpty()) {
            predicates.add(cb.like(root.get("askTitle"), "%" + request.getByTitle() + "%"));
        }


        if (request.getByWriter() != null && !request.getByWriter().isEmpty()) {
            Join<Question, Member> memberJoin = root.join("member", JoinType.INNER);
            predicates.add(cb.like(memberJoin.get("name"), "%" + request.getByWriter() + "%"));
        }

        // filter 조건 추가
        if (request.getStatus() != null) {
            switch (request.getStatus()) {
                case "done":
                    predicates.add(cb.isTrue(root.get("isAnswer")));
                    break;
                case "checking":
                    predicates.add(cb.isFalse(root.get("isAnswer")));
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
        if (request.getOrderDirection().equals("desc")) {
            cq.orderBy(cb.desc(root.get("editDate")));
        } else {
            cq.orderBy(cb.asc(root.get("editDate")));
        }

        // 6. 쿼리 실행 준비
        TypedQuery<Question> query = entityManager.createQuery(cq);

        // 7. 페이징 처리
        int page = request.getPage();
        int size = request.getSize();
        query.setFirstResult(page * size);  // 몇 번째부터
        query.setMaxResults(size);          // 몇 개 가져올지

        List<Question> resultList = query.getResultList();
        List<QnaItem> result = resultList.stream()
                .map(item -> new QnaItem.Builder(item).build())
                .collect(Collectors.toList());

        // 8. 전체 개수 구하는 count 쿼리 작성
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Question> countRoot = countQuery.from(Question.class);
        countQuery.select(cb.count(countRoot));

        // count 조건 동일하게 적용
        List<Predicate> countPredicates = new ArrayList<>();

        if(request.getByTitle() != null && !request.getByTitle().isEmpty()) {
            countPredicates.add(cb.like(countRoot.get("askTitle"), "%" + request.getByTitle() + "%"));
        }

        if (request.getByWriter() != null && !request.getByWriter().isEmpty()) {
            Join<Question, Member> countMemberJoin = countRoot.join("member", JoinType.INNER);
            countPredicates.add(cb.like(countMemberJoin.get("name"), "%" + request.getByWriter() + "%"));
        }

        if (request.getStatus() != null) {
            switch (request.getStatus()) {
                case "done":
                    countPredicates.add(cb.isTrue(countRoot.get("isAnswer")));
                    break;
                case "checking":
                    countPredicates.add(cb.isFalse(countRoot.get("isAnswer")));
                    break;
                case "all":
                default:
                    // 아무 조건도 추가하지 않음
                    break;
            }
        }

        countQuery.where(cb.and(countPredicates.toArray(new Predicate[0])));
        Long total = entityManager.createQuery(countQuery).getSingleResult();

        // 9. Page 객체로 리턴
        return new PageImpl<>(result, PageRequest.of(page, size), total);
    }

    public QnaResponse getQna(long id) {
        Question question = questionRepository.findById(id).orElseThrow(CQuestionNotFoundException::new);
        return new QnaResponse.Builder(question).build();
    }

    public QnaResponse setQna(QnaCreateRequest request) {
        Question question = questionRepository.findById(request.getQuestionId())
                .orElseThrow(CQuestionNotFoundException::new);

//        Optional<Answer> target = answerRepository.findByQuestion(question);

        Answer answer = answerRepository.findByQuestion(question)
                .map(existing -> {
                    existing.putAnswer(request);
                    return existing;
                })
                .orElseGet(() -> new Answer.Builder(request, question).build());

        answerRepository.save(answer);
        question.setIsAnswer(true);
        questionRepository.save(question);

        return new QnaResponse.Builder(question).build();
    }
}
