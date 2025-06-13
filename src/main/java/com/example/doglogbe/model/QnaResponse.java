package com.example.doglogbe.model;

import com.example.doglogbe.entity.Answer;
import com.example.doglogbe.entity.Member;
import com.example.doglogbe.entity.Question;
import com.example.doglogbe.interfaces.CommonModelBuilder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QnaResponse {
    private Long id;
    private String writer;
    private String askTitle;
    private String askContent;
    private LocalDate editDate;
    private Boolean isAnswer;

    private Long answerId;
    private String answerComment;
    private LocalDate answerDate;

    private QnaResponse(Builder builder) {
        this.id = builder.id;
        this.writer = builder.writer;
        this.askTitle = builder.askTitle;
        this.askContent = builder.askContent;
        this.editDate = builder.editDate;
        this.isAnswer = builder.isAnswer;

        this.answerId = builder.answerId;
        this.answerComment = builder.answerComment;
        this.answerDate = builder.answerDate;
    }

    public static class Builder implements CommonModelBuilder<QnaResponse> {
        private final Long id;
        private final String writer;
        private final String askTitle;
        private final String askContent;
        private final LocalDate editDate;
        private final Boolean isAnswer;

        private Long answerId;
        private String answerComment;
        private LocalDate answerDate;

        public Builder(Question item) {
            this.id = item.getId();
            this.writer = item.getMember().getName();
            this.askTitle = item.getAskTitle();
            this.askContent = item.getAskContent();
            this.editDate = item.getEditDate();
            this.isAnswer = item.getIsAnswer();

            if(item.getAnswer() != null) {
                this.answerId = item.getAnswer().getId();
                this.answerComment = item.getAnswer().getComment();
                this.answerDate = item.getAnswer().getEditDate();
            }
            System.out.println(item.getAnswer());
        }

    @Override
    public QnaResponse build() {
        return new QnaResponse(this);
    }
}
}
