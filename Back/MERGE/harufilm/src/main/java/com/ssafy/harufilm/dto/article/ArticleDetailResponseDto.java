package com.ssafy.harufilm.dto.article;

import com.ssafy.harufilm.entity.Article;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleDetailResponseDto {
    Article article;
    int likey;

    public static ArticleDetailResponseDto of(Article article, int likey){
        ArticleDetailResponseDto body = new ArticleDetailResponseDto();
        body.setArticle(article);
        body.setLikey(likey);
        return body;
    }
}
