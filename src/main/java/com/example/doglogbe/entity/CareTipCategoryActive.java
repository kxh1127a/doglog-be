package com.example.doglogbe.entity;

import com.example.doglogbe.enums.CareTipCategory;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "enabled_caretip_category")
@Immutable
@Getter
public class CareTipCategoryActive {
    @Id
    @Enumerated(EnumType.STRING) // 또는 EnumType.ORDINAL, DB와 일치하게
    @Column(name = "care_tip_category")
    private CareTipCategory careTipCategory;
}
