package com.ssafy.harufilm.repository.imgvid;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.harufilm.entity.Imgvid;

@Repository
public interface ImgvidRepository extends JpaRepository<Imgvid, Integer>{

    Optional<Imgvid> findByArticleidxAndImgvidnumber(int articleidx, int imgvidnumber);
    
}
