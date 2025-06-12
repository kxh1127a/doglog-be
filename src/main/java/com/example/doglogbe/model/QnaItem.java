package com.example.doglogbe.model;

import com.example.doglogbe.entity.Member;
import com.example.doglogbe.entity.Question;
import com.example.doglogbe.interfaces.CommonModelBuilder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class QnaItem {
    private Long id;
    private Member member;
    private String askTitle;
    private LocalDate editDate;
    private Boolean isAnswer;

    private QnaItem(Builder builder) {
        this.id = builder.id;
        this.member = builder.member;
        this.askTitle = builder.askTitle;
        this.editDate = builder.editDate;
        this.isAnswer = builder.isAnswer;
    }

    public static class Builder implements CommonModelBuilder<QnaItem> {
        private final Long id;
        private final Member member;
        private final String askTitle;
        private final LocalDate editDate;
        private final Boolean isAnswer;

        public Builder(Question item) {
            this.id = item.getId();
            this.member = item.getMember();
            this.askTitle = item.getAskTitle();
            this.editDate = item.getEditDate();
            this.isAnswer = item.getIsAnswer();
        }

        @Override
        public QnaItem build() {
            return new QnaItem(this);
        }
    }
}

