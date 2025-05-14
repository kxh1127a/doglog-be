package com.example.doglogbe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "member_id")
    private Member member;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = true, length = 100)
    private String title;

    @Column(nullable = true)
    private Double weight;

    @Column(nullable = true, length = 100)
    private String treatmentInfo;

    @Column(nullable = true)
    private LocalDate treatmentRepeat;

    @Column(nullable = true, length = 500)
    private String memo;
}
