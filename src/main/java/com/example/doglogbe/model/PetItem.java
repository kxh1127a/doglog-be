package com.example.doglogbe.model;

import com.example.doglogbe.entity.Pet;
import com.example.doglogbe.entity.PetBreed;
import com.example.doglogbe.enums.PetGender;
import com.example.doglogbe.interfaces.CommonModelBuilder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PetItem {
    private Long id;
    private String name;
    private LocalDate birthDate;
    private String petBreed;
    private Double weight;
    private PetGender gender;
    private String registrationNumber;

    private Long memberId;
    private String memberName;
    private String memberUserName;

    private PetItem(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.birthDate = builder.birthDate;
        this.petBreed = builder.petBreed;
        this.weight = builder.weight;
        this.gender = builder.gender;
        this.registrationNumber = builder.registrationNumber;
        this.memberId = builder.memberId;
        this.memberName = builder.memberName;
        this.memberUserName = builder.memberUserName;
    }

    public static class Builder implements CommonModelBuilder<PetItem> {
        private final Long id;
        private final String name;
        private final LocalDate birthDate;
        private final String petBreed;
        private final Double weight;
        private final PetGender gender;
        private final String registrationNumber;
        private final Long memberId;
        private final String memberName;
        private final String memberUserName;

        public Builder(Pet pet) {
            this.id = pet.getId();
            this.name = pet.getName();
            this.birthDate = pet.getBirthDate();
            this.petBreed = pet.getPetBreed().getBreedName();
            this.weight = pet.getWeight();
            this.gender = pet.getGender();
            this.registrationNumber = pet.getRegistrationNumber();
            this.memberId = pet.getMember().getId();
            this.memberName = pet.getMember().getName();
            this.memberUserName = pet.getMember().getUserName();
        }

        @Override
        public PetItem build() {
            return new PetItem(this);
        }
    }
}
