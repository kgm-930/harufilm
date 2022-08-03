package com.ssafy.harufilm.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT UNSIGNED")
    private int articleidx;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(insertable =  false, updatable = false, name = "userpid")
    private int userpid;

    @Column
    private int articlethumbnail;

    @Column
    @CreationTimestamp
    private LocalDateTime articlecreatedate;

}
