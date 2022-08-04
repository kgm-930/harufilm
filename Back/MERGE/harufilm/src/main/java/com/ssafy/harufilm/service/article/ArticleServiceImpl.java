package com.ssafy.harufilm.service.article;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.harufilm.dto.article.ArticleRequestDto;
import com.ssafy.harufilm.dto.article.ArticleStructDto;
import com.ssafy.harufilm.dto.hash.HashRequestDto;
import com.ssafy.harufilm.entity.Article;
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
        String userpid = articleRequestDto.getUserpid();
        String thumbnail = articleRequestDto.getArticlethumbnail();
        List<HashRequestDto> list = articleRequestDto.getHashlist();
        articleRequestDto.getImgvidlist();
        ArticleStructDto articleStructDto = new ArticleStructDto();
        articleStructDto.setUserpid(userpid);
        articleStructDto.setArticlethumbnail(thumbnail);

        Article a = articleSave(articleRequestDto);
        articleRepository.save(a);   
        
        return null;
    }

    @Override
    public void articleDelete(ArticleRequestDto articleRequestDto) {
        // TODO Auto-generated method stub
        
    }
    
}
