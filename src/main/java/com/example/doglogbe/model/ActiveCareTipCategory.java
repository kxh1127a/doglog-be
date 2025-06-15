package com.example.doglogbe.model;

import com.example.doglogbe.entity.CareTipCategoryActive;
import com.example.doglogbe.interfaces.CommonModelBuilder;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ActiveCareTipCategory {
    private String categoryName;

    private ActiveCareTipCategory(Builder builder){
        this.categoryName = builder.categoryName;
    }

    public static class Builder implements CommonModelBuilder<ActiveCareTipCategory>{
        private final String categoryName;

        public Builder(CareTipCategoryActive careTipCategoryActive){
            this.categoryName = careTipCategoryActive.getCareTipCategory().toString();
        }

        @Override
        public ActiveCareTipCategory build(){
            return new ActiveCareTipCategory(this);
        }

    }
}
