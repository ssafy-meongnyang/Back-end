package com.ssafy.meongnyang.api.pet.domain;

import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pet {
    private Long id;
    private Long userId;
    private String name;
    private String breed;
    private LocalDate birthDate;
    private String gender;
    private Integer weight;
    private String shape;
    private Boolean isAllergic;
    private String profileImageUrl;
    private Boolean isRepresentative;
    private String createdAt;
    private String updatedAt;
}
