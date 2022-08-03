package com.ssafy.harufilm.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
public class Likey {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT UNSIGNED")
    private int likeyidx;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(insertable =  false, updatable = false, name = "likeyfrom")
    private int likeyfrom;

    @ManyToOne(targetEntity = Article.class)
    @JoinColumn(insertable =  false, updatable = false, name = "likeyto")
    private int likeyto;
}
