package com.ssafy.harufilm.dto.article;

import java.util.List;

import com.ssafy.harufilm.dto.hash.HashRequestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleRequestDto {

    int userpid; // 아티클 작성 유저

    int articlethumbnail; // 아티클 썸네일

    int articleshare;// 아티클 공유 범위

    List<HashRequestDto> hashlist;// 해시태그 리스트

}
