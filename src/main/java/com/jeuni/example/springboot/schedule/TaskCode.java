package com.jeuni.example.springboot.schedule;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TaskCode implements CodeEnum {

    CREATE_BILL("CB"),
    SEND_VMG("SV"),
    CB("생성"),
    SV("발송");
    // .. 생략

    private final String code;
}
