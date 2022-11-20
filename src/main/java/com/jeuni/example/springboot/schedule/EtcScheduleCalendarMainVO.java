package com.jeuni.example.springboot.schedule;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
@EqualsAndHashCode(callSuper = false)
@ToString
public class EtcScheduleCalendarMainVO {

    private String scheSeq;
    private String jobSe;
    private String taskCode;
    private String taskStartDay;
    private String taskEndDay;

    // .. 생략

    public String getJobSe() {
        return JobSe.valueOf(this.jobSe).getText();
    }

    public String getTaskCode() {
        return TaskCode.valueOf(this.taskCode).getCode();
    }
}
