package com.jeuni.example.springboot.inspect;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@EqualsAndHashCode(callSuper = false)
@ToString
public class InspectMainDto {

    private int seq;
    private int hstSeq;
    private String jobSe;
    // .. 생략
}
