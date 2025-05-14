package com.example.doglogbe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class CareTip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "care_tip_category_id")
    private CareTipCategory careTipCategory;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 5000)
    private String content;

    @Column(nullable = false)
    private LocalDate editDate;

    @Column(nullable = false)
    private Integer like;

    @Column(nullable = false)
    private Boolean recommend;

    @Column(nullable = false)
    private Boolean isEnable;
}
