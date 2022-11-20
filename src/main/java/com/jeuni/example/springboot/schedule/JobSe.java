package com.jeuni.example.springboot.schedule;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum JobSe implements CodeEnum {

    SMT("SMT", "스마트폰"),
    FTR("FTR", "피쳐폰");
    // .. 생략

    private final String code;
    private final String text;
}
