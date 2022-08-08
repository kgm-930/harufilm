package com.ssafy.harufilm.service.article;

import java.io.IOException;
import java.util.List;

import com.ssafy.harufilm.dto.article.ArticleRequestDto;
import com.ssafy.harufilm.entity.Article;

public interface ArticleService {
    
    Article articleSave(ArticleRequestDto articleRequestDto) throws IllegalStateException, IOException; // 아티클 생성
    void articleDelete(int articleidx);// 아티클 삭제
    
    List<Article> getmyArticle(int userpid);

    List<Article> getFollowedArticleList(int userpid);

    // Article findByArticleidx(int articleidx);

    public List<Article> getarticlelistbykeyword(String keyword);
}
