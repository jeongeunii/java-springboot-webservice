package com.jeuni.example.springboot.web.dto;

import com.jeuni.example.springboot.domain.TCT.TCT;
import lombok.Getter;

@Getter
public class TctResponseDto {

    private String gubun_cd;
    private String inv_dt;
    private String svc_mgmt_num;
    private String rcv_svc_num;
    private Long pay_amt;
    private String cust_nm;
    private String create_yn;
    private String send_yn;

    public TctResponseDto(TCT entity) {
        this.gubun_cd = entity.getGubun_cd();
        this.inv_dt = entity.getInv_dt();
        this.svc_mgmt_num = entity.getSvc_mgmt_num();
        this.rcv_svc_num = entity.getRcv_svc_num();
        this.pay_amt = entity.getPay_amt();
        this.cust_nm = entity.getCust_nm();
        this.create_yn = entity.getCreate_yn();
        this.send_yn = entity.getSend_yn();
    }
}
