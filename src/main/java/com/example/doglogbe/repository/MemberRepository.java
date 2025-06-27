package com.example.doglogbe.repository;

import com.example.doglogbe.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Page<Member> findByIsEnabled(boolean isEnabled, Pageable pageable);

    Optional<Member> findByUserName(String userName);
}
