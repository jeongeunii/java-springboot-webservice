package com.jeuni.example.springboot.web.dto;

import com.jeuni.example.springboot.domain.samples.Samples;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SamplesSaveRequestDto {

    private String type;
    private String acnt_num;
    private String rcv_num;
    private String send_yn;

    @Builder
    public SamplesSaveRequestDto(String type, String acnt_num, String rcv_num, String send_yn) {
        this.type = type;
        this.acnt_num = acnt_num;
        this.rcv_num = rcv_num;
        this.send_yn = send_yn;
    }

    public Samples toEntity() {
        return Samples.builder()
                .type(type)
                .acnt_num(acnt_num)
                .rcv_num(rcv_num)
                .send_yn(send_yn)
                .build();
    }
}
