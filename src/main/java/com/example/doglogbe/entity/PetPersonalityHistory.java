package com.example.doglogbe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class PetPersonalityHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "pet_id")
    private Pet pet;

    @Column(nullable = false, length = 100)
    private Integer activityLevel;

    @Column(nullable = false, length = 100)
    private Integer sociability;

    @Column(nullable = false, length = 100)
    private Integer trainability;

    @Column(nullable = false, length = 100)
    private Integer adaptability;

    @Column(nullable = false, length = 100)
    private Integer neuroticism;

    @Column(nullable = false, length = 100)
    private Integer independence;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}
