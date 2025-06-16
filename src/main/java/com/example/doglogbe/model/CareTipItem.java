package com.example.doglogbe.model;

import com.example.doglogbe.entity.CareTip;
import com.example.doglogbe.interfaces.CommonModelBuilder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CareTipItem {

    private Long id;
    private String title;
    private String careTipCategory;
    private Integer careTipLike;
    private Boolean recommend;
    private Boolean isEnabled;
    private String imgUrl;

    private CareTipItem(Builder builder){
        this.id = builder.id;
        this.title = builder.title;
        this.careTipCategory = builder.careTipCategory;
        this.careTipLike = builder.careTipLike;
        this.recommend = builder.recommend;
        this.isEnabled = builder.isEnabled;
        this.imgUrl = builder.imgUrl;
    }

    public static class Builder implements CommonModelBuilder<CareTipItem> {
        private final Long id;
        private final String title;
        private final String careTipCategory;
        private final Integer careTipLike;
        private final Boolean recommend;
        private final Boolean isEnabled;
        private final String imgUrl;

        public Builder(CareTip careTip){
            this.id = careTip.getId();
            this.title = careTip.getTitle();
            this.careTipCategory = careTip.getCareTipCategory().toString();
            this.careTipLike = careTip.getCareTipLike();
            this.recommend = careTip.getRecommend();
            this.isEnabled = careTip.getIsEnabled();
            this.imgUrl = careTip.getImgUrl();
        }

        @Override
        public CareTipItem build(){
            return new CareTipItem(this);
        }

    }

    public static CareTipItem from(CareTip careTip) {
        return new Builder(careTip).build();
    }

}
