package com.ssafy.harufilm.service.article;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.harufilm.dto.article.ArticleRequestDto;
import com.ssafy.harufilm.dto.hash.HashRequestDto;
import com.ssafy.harufilm.entity.Article;
import com.ssafy.harufilm.entity.Hash;
import com.ssafy.harufilm.entity.Hashtag;
import com.ssafy.harufilm.repository.article.ArticleRepository;
import com.ssafy.harufilm.repository.hash.HashRepository;
import com.ssafy.harufilm.repository.hashtag.HashtagRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    HashRepository hashRepository;

    @Autowired
    HashtagRepository hashtagRepository;

    @Override
    public Article articleSave(ArticleRequestDto articleRequestDto) {
        Article article = Article.builder()
                .articlethumbnail(articleRequestDto.getArticlethumbnail())
                .articleshare(articleRequestDto.getArticlethumbnail())
                .userpid(articleRequestDto.getUserpid())
                .build();

        Article savedArticle = articleRepository.save(article);
        // 아티클 저장

        List<HashRequestDto> hashlist = articleRequestDto.getHashlist();

        for (int i = 0; i < hashlist.size(); i++) {
            String hashname = hashlist.get(i).getHashname();

            Hash h;
            h = hashRepository.findByHashname(hashname).orElse(null);

            if (h == null) {
                h = Hash.builder()
                        .hashname(hashname)
                        .build();

                hashRepository.save(h);
            }
            Hashtag ht;
            ht = Hashtag.builder()
                    .hashidx(h.getHashidx())
                    .articleidx(savedArticle.getArticleidx())
                    .build();
            hashtagRepository.save(ht);
        }

        return savedArticle;
    }

    @Override
    public void articleDelete(int articleidx) {
        Article article = articleRepository.findByArticleidx(articleidx).orElse(null);

        if (article != null) {
            hashtagRepository.deleteAllByArticleidx(article.getArticleidx()); // 해시태그리스트에서 삭제
            // 이미지비디오리스트에서 삭제
            // 서버에 올라갔던 파일 삭제 추가해야 함
        }

    }

    @Override
    public List<Article> getmyArticle(int userpid) {
        List<Article> list = 
        articleRepository.findAllByUserpid(userpid);
        
        return list;
    }

    @Override
    public List<Article> getFollowedArticleList(int userpid) {
        List<Article> list = 
        articleRepository.articleList(userpid);
        
        return list;
    }

    @Override
    public Article findByArticleidx(int articleidx) {
        Article article = articleRepository.findByArticleidx(articleidx).orElse(null);
        return article;
    }
}
