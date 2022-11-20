package com.jeuni.example.springboot.schedule;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
@EqualsAndHashCode(callSuper = false)
@ToString
public class EtcScheduleCalendarJobVO {

    private String taskStatus;

    // .. 생략

    public String getTaskStatus() {
        return JobStatus.valueOf(this.taskStatus).getCode();
    }
}
