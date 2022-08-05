package com.ssafy.harufilm.service.article;

import com.ssafy.harufilm.dto.article.ArticleRequestDto;
import com.ssafy.harufilm.entity.Article;

public interface ArticleService {
    
    Article articleSave(ArticleRequestDto articleRequestDto); // 아티클 생성
    void articleDelete(int articleidx);// 아티클 삭제
    
}
