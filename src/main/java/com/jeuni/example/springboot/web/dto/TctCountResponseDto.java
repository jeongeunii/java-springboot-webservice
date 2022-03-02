package com.jeuni.example.springboot.web.dto;

import com.jeuni.example.springboot.domain.TCT.ITCT_COUNT;
import lombok.Getter;

@Getter
public class TctCountResponseDto {

    private String yn;
    private Long count;

    public TctCountResponseDto(ITCT_COUNT entity) {
        this.yn = entity.getYn();
        this.count = entity.getCount();
    }
}
