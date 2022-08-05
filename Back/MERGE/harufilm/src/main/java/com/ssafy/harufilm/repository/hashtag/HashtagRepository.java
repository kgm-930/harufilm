package com.ssafy.harufilm.repository.hashtag;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.ssafy.harufilm.entity.Article;
import com.ssafy.harufilm.entity.Hashtag;


@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, Integer>{
    // @Query("select article from Article a where a.articleidx = (select articleidx from Hashtag ht where ht.hashidx = (select hashidx from Hash h h.hasname = :hashname)))")
    // List<Article> findByHashname(String hashname);

    void findAllByArticleidx(int articleidx);

    void deleteAllByArticleidx(int articleidx);
    
}
