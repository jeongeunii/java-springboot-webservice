package com.jeuni.example.springboot.web.dto;

import com.jeuni.example.springboot.domain.jobs.Jobs;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class JobsSaveRequestDto {

    private String name;
    private String state;
    private String date;
    private String time;

    @Builder
    public JobsSaveRequestDto(String name, String state, String date, String time) {
        this.name = name;
        this.state = state;
        this.date = date;
        this.time = time;
    }

    public Jobs toEntity() {
        return Jobs.builder()
                .name(name)
                .state(state)
                .date(date)
                .time(time)
                .build();
    }
}
