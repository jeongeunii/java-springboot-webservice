package com.jeuni.example.springboot.schedule;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
@EqualsAndHashCode(callSuper = false)
@ToString
public class EtcScheduleCalendarComVO {

    private int scheSn;
    private String jobSe;
    private String taskStartDay;
    private String taskEndDay;

    // .. 생략
}
