package com.jeuni.example.springboot.schedule;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
@EqualsAndHashCode(callSuper = false)
@ToString
public class EtcScheduleByDayDto {

    private String jobSe;
    private String taskCode;
    private String taskStartDay;
    private String taskEndDay;
}
