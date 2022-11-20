package com.jeuni.example.springboot.schedule;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
@EqualsAndHashCode(callSuper = false)
@ToString
public class EtcScheduleHolidayVO {
    
    private String holidaySeq;
    private String holidayNm;
    private String holidayDt;
}
