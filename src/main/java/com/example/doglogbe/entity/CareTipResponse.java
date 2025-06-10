package com.example.doglogbe.entity;

import com.example.doglogbe.interfaces.CommonModelBuilder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CareTipResponse {
    private Long id;
    private String careTipCategory;
    private String title;
    private String content;
    private Integer editDate;
    private Integer careTipLike;
    private Boolean recommend;
    private Boolean isEnabled;

    private CareTipResponse(Builder builder){
        this.id = builder.id;
        this.careTipCategory = builder.careTipCategory;
        this.title = builder.title;
        this.content = builder.content;
        this.editDate = builder.editDate;
        this.careTipLike = builder.careTipLike;
        this.recommend = builder.recommend;
        this.isEnabled = builder.isEnabled;
    }

    public static class Builder implements CommonModelBuilder<CareTipResponse> {

        private final Long id;
        private final String careTipCategory;
        private final String title;
        private final String content;
        private final Integer editDate;
        private final Integer careTipLike;
        private final Boolean recommend;
        private final Boolean isEnabled;

        public Builder(CareTip careTip){
            this.id = careTip.getId();
            this.careTipCategory = careTip.getCareTipCategory().toString();
            this.title = careTip.getTitle();
            this.content = careTip.getContent();
            this.editDate = careTip.getEditDate().getYear();
            this.careTipLike = careTip.getCareTipLike();
            this.recommend = careTip.getRecommend();
            this.isEnabled = careTip.getIsEnabled();
        }

        @Override
        public CareTipResponse build() {
            return new CareTipResponse(this);
        }
    }



}
