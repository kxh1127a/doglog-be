package com.example.doglogbe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "question_id")
    private Question question;

    @Column(nullable = false, length = 5000)
    private String comment;

    @Column(nullable = false)
    private LocalDate editDate;

    @Column(nullable = false)
    private Boolean isEnable;
}
