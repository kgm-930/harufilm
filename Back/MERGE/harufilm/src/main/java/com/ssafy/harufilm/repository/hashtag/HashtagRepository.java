package com.ssafy.harufilm.repository.hashtag;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.harufilm.entity.Hashtag;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, Integer>{
    Optional<Hashtag> findByHashtagname(String hashname);

    void findAllByArticleidx(int articleidx);

    void deleteAllByArticleidx(int articleidx);
    
}
