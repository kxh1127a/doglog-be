package com.example.doglogbe.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "enabled_caretip_category")
@Immutable
@Getter
public class CareTipCategoryActive {
    @Id
    private Long id;
}
