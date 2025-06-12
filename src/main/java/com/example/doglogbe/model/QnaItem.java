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
    private String askTitle;
    private LocalDate editDate;
    private Boolean isAnswer;

    private Long memberId;
    private String memberName;
    private String memberUserName;

    private QnaItem(Builder builder) {
        this.id = builder.id;
        this.askTitle = builder.askTitle;
        this.editDate = builder.editDate;
        this.isAnswer = builder.isAnswer;

        this.memberId = builder.memberId;
        this.memberName = builder.memberName;
        this.memberUserName = builder.memberUserName;
    }

    public static class Builder implements CommonModelBuilder<QnaItem> {
        private final Long id;
        private final String askTitle;
        private final LocalDate editDate;
        private final Boolean isAnswer;

        private final Long memberId;
        private final String memberName;
        private final String memberUserName;

        public Builder(Question item) {
            this.id = item.getId();
            this.askTitle = item.getAskTitle();
            this.editDate = item.getEditDate();
            this.isAnswer = item.getIsAnswer();

            this.memberId = item.getMember().getId();
            this.memberName = item.getMember().getName();
            this.memberUserName = item.getMember().getUserName();
        }

        @Override
        public QnaItem build() {
            return new QnaItem(this);
        }
    }
}

