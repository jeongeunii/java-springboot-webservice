package com.jeuni.example.springboot.domain.TCT;

import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Entity
public class TCT_COUNT implements Serializable {

    @Id
    @Column(name = "yn")
    private String yn;

    @Column(name = "count")
    private Long count;

}
