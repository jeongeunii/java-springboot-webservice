package com.jeuni.example.springboot.web.dto;

import com.jeuni.example.springboot.domain.jobs.Jobs;
import lombok.Getter;

@Getter
public class JobsListResponseDto {

    private Long id;
    private String name;
    private String state;
    private String date;
    private String time;

    public JobsListResponseDto(Jobs entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.state = entity.getState();
        this.date = entity.getDate();
        this.time = entity.getTime();
    }
}
