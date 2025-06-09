package com.example.doglogbe.entity;

import com.example.doglogbe.interfaces.CommonModelBuilder;
import com.example.doglogbe.model.CareTipCreateRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CareTip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "care_tip_category_id")
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

    private CareTip(Builder builder) {
        this.careTipCategory = builder.careTipCategory;
        this.title = builder.title;
        this.content = builder.content;
        this.editDate = builder.editDate;
    }

    public static class Builder implements CommonModelBuilder<CareTip> {

        private final CareTipCategory careTipCategory;
        private final String title;
        private final String content;
        private final LocalDate editDate;

        public Builder(CareTipCreateRequest careTipCreateRequest) {
            this.careTipCategory = careTipCreateRequest.getCareTipCategory();
            this.title = careTipCreateRequest.getTitle();
            this.content = careTipCreateRequest.getContent();
            this.editDate = LocalDate.now();
        }

        @Override
        public CareTip build() {
            return new CareTip(this);
        }

    }
}
