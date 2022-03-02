package com.jeuni.example.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class JobsUpdateRequestDto {

    private String name;
    private String state;
    private String date;
    private String time;

    @Builder
    public JobsUpdateRequestDto(String name, String state, String date, String time) {
        this.name = name;
        this.state = state;
        this.date = date;
        this.time = time;
    }
}
