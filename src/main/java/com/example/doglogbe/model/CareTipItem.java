package com.example.doglogbe.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CareTipItem {

    private Long id;
    private String title;
    private String careTipCategory;
    private Integer careTipLike;
    private Boolean recommend;
    private Boolean isEnabled;

//    public static class Builder {
//        private final Long id;
//        private final String title;
//        private final String careTipCategory;
//        private final Integer careTipLike;
//        private final Boolean recommend;
//        private final Boolean isEnabled;
//    }

}
