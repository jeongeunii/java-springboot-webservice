package com.jeuni.example.springboot.schedule;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@EqualsAndHashCode(callSuper = false)
@ToString
public class EtcScheduleMainDto {

    private int scheSeq;
    private int scheSn;
    private String jobSe;
    private String taskCode;
    private String taskStartDay;
    private String taskEndDay;
    private String holidayYn;
    private String taskSn;

    // .. 생략
}
