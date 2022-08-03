package com.ssafy.harufilm.repository.likey;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.harufilm.entity.Likey;

@Repository
public interface LikeyRepository extends JpaRepository<Likey, Integer>{

    Likey findByLikeyfromAndLikeyto(int likeyfrom, int likeyto); //user(from) article(to)

}
