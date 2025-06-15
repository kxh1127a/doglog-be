package com.example.doglogbe.model;

import com.example.doglogbe.entity.CareTipCategoryActive;
import com.example.doglogbe.interfaces.CommonModelBuilder;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ActiveCareTipCategory {
    private String categoryName;
    private String category;

    private ActiveCareTipCategory(Builder builder){
        this.categoryName = builder.categoryName;
        this.category = builder.category;
    }

    public static class Builder implements CommonModelBuilder<ActiveCareTipCategory>{
        private final String categoryName;
        private final String category;

        public Builder(CareTipCategoryActive careTipCategoryActive){
            this.categoryName = careTipCategoryActive.getCareTipCategory().getName();
            this.category = careTipCategoryActive.getCareTipCategory().toString();
        }

        @Override
        public ActiveCareTipCategory build(){
            return new ActiveCareTipCategory(this);
        }

    }
}
