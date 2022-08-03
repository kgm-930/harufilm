package com.ssafy.harufilm.repository.article;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssafy.harufilm.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Integer>{
    //내가 작성한 글 리스트 반환
    List<Article> findAllByUserpid(int userpid); 
    //내가 구독한 사람들의 글 리스트
    @Query("select a from Article a where a.userpid = (select s.subto from Subscribe s where s.subfrom = :userpid )")
    List<Article> findAllBySubscribe(@Param("userpid") int userpid);
    //아티클 하나 가져오기
    Optional<Article> findByArticleidx(int articleidx);
}
