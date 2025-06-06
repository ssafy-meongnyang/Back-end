package com.ssafy.meongnyang.api.pet.domain;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

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
    private Boolean allergic;

    private List<String> healthConcerns;
    private List<String> allergens;

    private String profileImagePath;
    private boolean representative;

    private String createdAt;
    private String updatedAt;

}
