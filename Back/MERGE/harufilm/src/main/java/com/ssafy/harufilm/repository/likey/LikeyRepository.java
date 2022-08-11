package com.ssafy.harufilm.repository.likey;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.harufilm.entity.Likey;

@Repository
public interface LikeyRepository extends JpaRepository<Likey, Integer>{

    Optional<Likey> findByLikeyfromAndLikeyto(int likeyfrom, int likeyto); //user(from) article(to)

    List<Likey> findByLikeyfrom(int likeyfrom);

    List<Likey> findByLikeyto(int likeyto);

}
