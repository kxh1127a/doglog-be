package com.example.doglogbe.model;

import com.example.doglogbe.entity.Member;
import com.example.doglogbe.entity.Pet;
import com.example.doglogbe.entity.PetBreed;
import com.example.doglogbe.enums.MemberRole;
import com.example.doglogbe.enums.PetGender;
import com.example.doglogbe.interfaces.CommonModelBuilder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberResponse {
    private Long id;
    private String name;
    private String userName;
    private String email;
    private String phone;
    private LocalDateTime createdAt;
    private MemberRole role;

    private List<PetResponse> pets;

    private Long countTipLike;
    private Long countQuestion;

    private MemberResponse(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.userName = builder.userName;
        this.email = builder.email;
        this.phone = builder.phone;
        this.createdAt = builder.createdAt;
        this.role = builder.role;

        this.pets = builder.pets;

        this.countTipLike = builder.countTipLike;
        this.countQuestion = builder.countQuestion;
    }

    public static class Builder implements CommonModelBuilder<MemberResponse> {
        private final Long id;
        private final String name;
        private final String userName;
        private final String email;
        private final String phone;
        private final LocalDateTime createdAt;
        private final MemberRole role;
        private final List<PetResponse> pets;
        private final Long countTipLike;
        private final Long countQuestion;


        public Builder(Member member, long countTipLike, long countQuestion) {
            this.id = member.getId();
            this.name = member.getName();
            this.userName = member.getUserName();
            this.email = member.getEmail();
            this.phone = member.getPhone();
            this.createdAt = member.getCreatedAt();
            this.role = member.getRole();

            this.pets = member.getPets().stream()
                    .map(PetResponse::new)
                    .toList();

            this.countTipLike = countTipLike;
            this.countQuestion = countQuestion;
        }

        @Override
        public MemberResponse build() {
            return new MemberResponse(this);
        }
    }

}
