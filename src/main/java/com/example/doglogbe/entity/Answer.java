package com.example.doglogbe.entity;

import com.example.doglogbe.interfaces.CommonModelBuilder;
import com.example.doglogbe.model.QnaCreateRequest;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonManagedReference
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "question_id")
    private Question question;

    @Column(nullable = false, length = 5000)
    private String comment;

    @Column(nullable = false)
    private LocalDate editDate;

    @Column(nullable = false)
    private Boolean isEnabled;

    private Answer(Builder builder) {
        this.question = builder.question;
        this.comment = builder.comment;
        this.editDate = builder.editDate;
        this.isEnabled = builder.isEnabled;
    }

    public static class Builder implements CommonModelBuilder<Answer> {
        private final Question question;
        private final String comment;
        private final LocalDate editDate;
        private final Boolean isEnabled;

        public Builder(QnaCreateRequest request, Question question) {
            this.question = question;
            this.comment = request.getComment();
            this.editDate = LocalDate.now();
            this.isEnabled = true;
        }

        @Override
        public Answer build() {
            return new Answer(this);
        }
    }
}
