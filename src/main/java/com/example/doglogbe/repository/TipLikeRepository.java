package com.example.doglogbe.repository;

import com.example.doglogbe.entity.TipLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipLikeRepository extends JpaRepository<TipLike, Long> {
    long countAllByMemberId(long id);
}
