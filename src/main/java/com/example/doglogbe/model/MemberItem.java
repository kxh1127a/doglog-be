package com.example.doglogbe.model;

import com.example.doglogbe.entity.Member;
import com.example.doglogbe.entity.Pet;
import com.example.doglogbe.enums.MemberRole;
import com.example.doglogbe.interfaces.CommonModelBuilder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberItem {
    private Long id;
    private MemberRole memberRole;
    private String name;
    private String userName;
    private String email;
    private String phone;
    private String petName;
    private String petBirthDate;
    private LocalDate createdAt;
    private String lastLoginInfo;
    private Boolean isEnabled;
    private String petProfileImageUrl;

    private MemberItem (Builder builder) {
        this.id = builder.id;
        this.memberRole = builder.memberRole;
        this.name = builder.name;
        this.userName = builder.userName;
        this.email = builder.email;
        this.phone = builder.phone;
        this.petName = builder.petName;
        this.petBirthDate = builder.petBirthDate;
        this.createdAt = builder.createdAt;
        this.lastLoginInfo = builder.lastLoginInfo;
        this.isEnabled = builder.isEnabled;
        this.petProfileImageUrl = builder.petProfileImageUrl;
    }

    public static class Builder implements CommonModelBuilder<MemberItem> {
        private final Long id;
        private final MemberRole memberRole;
        private final String name;
        private final String userName;
        private final String email;
        private final String phone;
        private final String petName;
        private final String petBirthDate;
        private final LocalDate createdAt;
        private final String lastLoginInfo;
        private final Boolean isEnabled;
        private final String petProfileImageUrl;

        public Builder(Member item) {
            this.id = item.getId();
            this.memberRole = item.getRole();
            this.name = item.getName();
            this.userName = item.getUserName();
            this.email = item.getEmail();
            this.phone = item.getPhone();
            this.createdAt = item.getCreatedAt().toLocalDate();
            this.isEnabled = item.getIsEnabled();

            if(!item.getPets().isEmpty()) {
                Pet pet = item.getPets().get(0);
                this.petName = pet.getName();
                this.petProfileImageUrl= pet.getProfileImageUrl();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                this.petBirthDate = pet.getBirthDate().format(formatter);
            } else {
                this.petName = "";
                this.petProfileImageUrl = "";
                this.petBirthDate = "";
            }

            if(item.getLastLoginAt() != null) {
                long days = ChronoUnit.DAYS.between(item.getLastLoginAt().toLocalDate(), LocalDate.now());
                this.lastLoginInfo = days + " days ago";
            } else {
                this.lastLoginInfo = "기록 없음";
            }
        }

        @Override
        public MemberItem build() {
            return new MemberItem(this);
        }
    }
}
