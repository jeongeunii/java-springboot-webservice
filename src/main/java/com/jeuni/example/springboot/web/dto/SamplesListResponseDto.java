package com.jeuni.example.springboot.web.dto;

import com.jeuni.example.springboot.domain.samples.Samples;
import lombok.Getter;

@Getter
public class SamplesListResponseDto {

    private Long id;
    private String type;
    private String acnt_num;
    private String rcv_num;
    private String send_yn;

    public SamplesListResponseDto(Samples entity) {
        this.id = entity.getId();
        this.type = entity.getType();
        this.acnt_num = entity.getAcnt_num();
        this.rcv_num = entity.getRcv_num();
        this.send_yn = entity.getSend_yn();
    }
}
