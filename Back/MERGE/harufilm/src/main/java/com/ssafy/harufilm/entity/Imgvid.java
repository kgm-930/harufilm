package com.ssafy.harufilm.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Imgvid {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT UNSIGNED")
    private int imgvididx;

    @Column
    private int imgvidnumber;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(insertable =  false, updatable = false, name = "articleidx")
    private int articleidx;

    @Column
    private String imgvidlocation;

    @Column
    private LocalDateTime imgviddate;

    @Column
    private String imgpath;

    @Column
    private String vidpath;
}
