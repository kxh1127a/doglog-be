package com.example.doglogbe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "member_id")
    private Member member;

    @Column(nullable = false)
    private String askTitle;

    @Column(nullable = false, length = 5000)
    private String askContent;

    @Column(nullable = false)
    private LocalDate editDate;

    @Column(nullable = false)
    private Boolean isAnswer;

    @Column(nullable = false)
    private Boolean isEnabled;
}
