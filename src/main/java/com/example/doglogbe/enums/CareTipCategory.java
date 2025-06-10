package com.example.doglogbe.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CareTipCategory {
    HEALTH_CARE(1,"건강관리"),
    TRAINING_EDUCATION(2,"훈련·교육"),
    NUTRITION(3,"사료·간식·영양"),
    BEGINNER_GUIDE(4,"초보 애견인을 위한 핵심 가이드"),
    SUPPLIES(5,"애견 용품"),
    GROOMING_HYGIENE(6,"위생/미용"),
    LIVING_ENVIRONMENT(7,"생활환경/주거"),
    ADOPTION(8,"분양/입양"),
    INSURANCE_COST(9,"보험/비용"),
    LAW_POLICY_REGISTRATION(10,"법/정책/등록"),
    SOCIALIZATION_PLAY_EXERCISE(11,"사회화/놀이·운동");

    private final Integer code;
    private final String name;
}
