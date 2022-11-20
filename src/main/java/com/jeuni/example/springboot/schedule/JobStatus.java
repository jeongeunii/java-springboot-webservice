package com.jeuni.example.springboot.schedule;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum JobStatus implements CodeEnum {

    PR("작업중"),
    CO("작업완료");
    // .. 생략

    private final String code;
}
