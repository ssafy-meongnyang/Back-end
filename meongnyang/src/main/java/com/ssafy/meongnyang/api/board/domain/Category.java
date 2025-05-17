package com.ssafy.meongnyang.api.board.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {
    DIET_RECOMMEND("diet_recommend"),
    MEONGNYANG_BOAST("meongnyang_boast"),
    HEALTH_CARE("health_care"),
    ;

    private final String category;

}
