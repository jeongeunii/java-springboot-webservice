package com.jeuni.example.springboot.domain.jobs;

import com.jeuni.example.springboot.web.dto.JobsUpdateRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Jobs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String name;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private String time;

    @Builder
    public Jobs(String name, String state, String date, String time) {
        this.name = name;
        this.state = state;
        this.date = date;
        this.time = time;
    }

    public void update(JobsUpdateRequestDto requestDto) {
        this.name = requestDto.getName();
        this.state = requestDto.getState();
        this.date = requestDto.getDate();
        this.time = requestDto.getTime();
    }
}
