package com.ssafy.harufilm.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.harufilm.common.ErrorResponseBody;
import com.ssafy.harufilm.common.MessageBody;
import com.ssafy.harufilm.dto.article.ArticleDetailRequestDto;
import com.ssafy.harufilm.dto.article.ArticleDetailResponseDto;
import com.ssafy.harufilm.dto.article.ArticleRequestDto;
import com.ssafy.harufilm.dto.article.ArticleShareRequestDto;
import com.ssafy.harufilm.dto.article.ArticleShowRequestDto;
import com.ssafy.harufilm.entity.Article;
import com.ssafy.harufilm.service.article.ArticleService;

@RestController
@RequestMapping("/api/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @PostMapping("/create")
    public ResponseEntity<?> setArticle(@Validated ArticleRequestDto articleRequestDto) {

        System.out.println(articleRequestDto.getUserpid());
        try {
            articleService.articleSave(articleRequestDto);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ErrorResponseBody.of(500, false,
                    "Interanl Server Error, 글 저장 실패"));
        }
        return ResponseEntity.status(200).body(MessageBody.of(true, "글 저장 성공"));
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteArticle(@RequestBody ArticleShareRequestDto articleShareRequestDto) {
        try {
            articleService.articleDelete(articleShareRequestDto.getArticleidx());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ErrorResponseBody.of(500, false,
                    "Interanl Server Error, 글 삭제 실패"));
        }
        return ResponseEntity.status(200).body(MessageBody.of(true, "글 삭제 성공"));
    }

    @PostMapping("/sharecontrol")
    public ResponseEntity<?> sharecontrol(@RequestBody ArticleShareRequestDto articleShareRequestDto) {
        try {
            articleService.SetShare(articleShareRequestDto);

        } catch (Exception e) {
            return ResponseEntity.status(500).body(ErrorResponseBody.of(500, false,
                    "Interanl Server Error,공유 설정 실패"));
            // TODO: handle exception
        }
        return ResponseEntity.status(200).body(MessageBody.of(true, "공유 설정 완료"));
    }

    @PostMapping("/showarticle") // 일단 article정보 모두 가져오는 걸로
    public ResponseEntity<?> showarticle(@RequestBody ArticleShowRequestDto articleShowRequestDto) {

        List<Article> articles;
        List<ArticleDetailResponseDto> list = new ArrayList<>();
        try {
            articles = articleService.getArticle(articleShowRequestDto);
            for(int i = 0; i < articles.size(); i++){
                ArticleDetailResponseDto temp = new ArticleDetailResponseDto();
                temp.setArticle(articles.get(i));
                temp.setLikey(articleService.getLikey(articles.get(i).getArticleidx()));
                list.add(temp);
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ErrorResponseBody.of(500, false,
                    "Interanl Server Error, 글 목록 불러오기 실패"));
        }
        return ResponseEntity.status(200).body(list);
    }

    @PostMapping("/showsubarticle")
    public ResponseEntity<?> list(@RequestBody ArticleRequestDto articleRequestDto) {
        List<Article> articles;
        try {
            articles = articleService.getFollowedArticleList(articleRequestDto.getUserpid());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ErrorResponseBody.of(500, false,
                    "Interanl Server Error, 글 목록 불러오기 실패"));
        }
        return ResponseEntity.status(200).body(articles);
    }

    @PostMapping("/getarticle")
    public ResponseEntity<?> getArticle(@RequestBody ArticleDetailRequestDto articleDetailRequestDto){
    Article article;
    int likey;
        try{
        article = articleService.findByArticleidx(articleDetailRequestDto.getArticleidx());
        likey = articleService.getLikey(articleDetailRequestDto.getArticleidx());
    }catch(Exception e){
    return ResponseEntity.status(500).body(ErrorResponseBody.of(500, false,
    "Interanl Server Error, 글 불러오기 실패"));
    }
    return ResponseEntity.status(200).body(ArticleDetailResponseDto.of(article, likey));
    }
}
