package com.jeuni.example.springboot.domain.TCT;

import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Entity
//@Table(name = "TCT_SAM_COM")
public class TCT implements Serializable {

    @Column(name = "gubun_cd")
    private String gubun_cd;

    @Column(name = "inv_dt")
    private String inv_dt;

    @Id
    @Column(name = "svc_mgmt_num", nullable = false)
    private String svc_mgmt_num;

    @Column(name = "rcv_svc_num")
    private String rcv_svc_num;

    @Column(name = "svc_num")
    private String svc_num;

    @Column(name = "pay_amt")
    private Long pay_amt;

    @Column(name = "cust_nm")
    private String cust_nm;

    @Column(name = "create_yn")
    private String create_yn;

    @Column(name = "send_yn")
    private String send_yn;

}
