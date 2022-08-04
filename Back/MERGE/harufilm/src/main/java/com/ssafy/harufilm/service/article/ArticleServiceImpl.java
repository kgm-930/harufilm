package com.ssafy.harufilm.service.article;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.harufilm.dto.article.ArticleRequestDto;
import com.ssafy.harufilm.dto.hash.HashRequestDto;
import com.ssafy.harufilm.dto.imgvid.ImgvidRequestDto;
import com.ssafy.harufilm.entity.Article;
import com.ssafy.harufilm.entity.Hash;
import com.ssafy.harufilm.entity.Hashtag;
import com.ssafy.harufilm.entity.Imgvid;
import com.ssafy.harufilm.repository.article.ArticleRepository;
import com.ssafy.harufilm.repository.hash.HashRepository;
import com.ssafy.harufilm.repository.hashtag.HashtagRepository;
import com.ssafy.harufilm.repository.imgvid.ImgvidRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService{

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    HashRepository hashRepository;

    @Autowired
    HashtagRepository hashtagRepository;

    @Autowired
    ImgvidRepository imgvidRepository;

    @Override
    public Article articleSave(ArticleRequestDto articleRequestDto) {
        Article article = Article.builder()
        .articlethumbnail(articleRequestDto.getArticlethumbnail())
        .articleshare(articleRequestDto.getArticlethumbnail())
        .build();

        Article savedArticle = articleRepository.save(article);
        //아티클 저장
        
        List<HashRequestDto> hashlist = articleRequestDto.getHashlist();
        
        for(int i = 0; i < hashlist.size(); i++){
            String hashname = hashlist.get(i).getHashname();

            Hash h;
            h = hashRepository.findByHashname(hashname).orElse(null);

            if(h==null){
                h = Hash.builder()
                .hashname(hashname)
                .build();

                hashRepository.save(h);
            }
            Hashtag ht;
            ht = Hashtag.builder()
            .articleidx(savedArticle.getArticleidx())
            .build();
            hashtagRepository.save(ht);
        }

        List<ImgvidRequestDto> imgvidlist = articleRequestDto.getImgvidlist();

        for(int i = 1; i <= imgvidlist.size(); i++){
            String articleimg = imgvidlist.get(i).getArticleimg();
            String articlevid = imgvidlist.get(i).getArticlevid();
            String imgvidlocation = imgvidlist.get(i).getImgvidlocation();
            LocalDateTime imgviddate = imgvidlist.get(i).getImgviddate();

            Imgvid iv = Imgvid.builder()
            .imgvidnumber(i)
            .img(articleimg)
            .vid(articlevid)
            .articleidx(savedArticle.getArticleidx())
            .imgviddate(imgviddate)
            .imgvidlocation(imgvidlocation)
            .build();

            imgvidRepository.save(iv);
        }
        return savedArticle;
    }

    @Override
    public void articleDelete(ArticleRequestDto articleRequestDto) {
        // TODO Auto-generated method stub
        
    }
    
}
