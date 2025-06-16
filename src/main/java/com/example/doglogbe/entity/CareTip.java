package com.example.doglogbe.entity;

import com.example.doglogbe.enums.CareTipCategory;
import com.example.doglogbe.interfaces.CommonModelBuilder;
import com.example.doglogbe.model.CareTipCreateRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CareTip {
    private static final String DEFAULT_IMAGE_URL = "/uploads/caretip/default.jpg";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private CareTipCategory careTipCategory;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 5000)
    private String content;

    @Column(nullable = false)
    private LocalDate editDate;

    @Column
    private Integer careTipLike = 0;

    @Column
    private Boolean recommend = false;

    @Column
    private Boolean isEnabled = true;

    @Column(length = 500)
    private String imgUrl = DEFAULT_IMAGE_URL;

    private CareTip(Builder builder) {
        this.careTipCategory = builder.careTipCategory;
        this.title = builder.title;
        this.content = builder.content;
        this.editDate = builder.editDate;
        this.imgUrl = builder.imgUrl != null ? builder.imgUrl : DEFAULT_IMAGE_URL;
    }

    public static class Builder implements CommonModelBuilder<CareTip> {

        private final CareTipCategory careTipCategory;
        private final String title;
        private final String content;
        private final LocalDate editDate;
        private String imgUrl;

        public Builder(CareTipCreateRequest careTipCreateRequest) {
            this.careTipCategory = careTipCreateRequest.getCareTipCategory();
            this.title = careTipCreateRequest.getTitle();
            this.content = careTipCreateRequest.getContent();
            this.editDate = LocalDate.now();
        }

        public Builder imgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
            return this;
        }

        @Override
        public CareTip build() {
            return new CareTip(this);
        }
    }
}
