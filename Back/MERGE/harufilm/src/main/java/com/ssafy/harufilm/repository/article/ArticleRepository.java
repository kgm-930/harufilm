package com.ssafy.harufilm.repository.article;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.harufilm.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Integer>{
    
}
