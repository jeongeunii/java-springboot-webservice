package com.jeuni.example.springboot.domain.samples;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Samples {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String type;

    @Column(nullable = false)
    private String acnt_num;

    @Column(nullable = false)
    private String rcv_num;

    @Column(nullable = false)
    private String send_yn;

    @Builder
    public Samples(String type, String acnt_num, String rcv_num, String send_yn) {
        this.type = type;
        this.acnt_num = acnt_num;
        this.rcv_num = rcv_num;
        this.send_yn = send_yn;
    }
}
