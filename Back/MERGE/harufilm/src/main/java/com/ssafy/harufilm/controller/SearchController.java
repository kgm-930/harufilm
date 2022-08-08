package com.ssafy.harufilm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.harufilm.common.MessageBody;
import com.ssafy.harufilm.dto.account.SmallProfileResponseDto;
import com.ssafy.harufilm.dto.account.UserSearchResponseDto;
import com.ssafy.harufilm.dto.article.ArticleSearchResponseDto;
import com.ssafy.harufilm.entity.Article;
import com.ssafy.harufilm.service.article.ArticleService;
import com.ssafy.harufilm.service.user.UserService;

@RestController
@RequestMapping("/api/search")
public class SearchController {
    //해당 키워드를 포함한 userid의 사용자 목록(userid, username, userimg),
    //해당 키워드를 포함한 hashname의 게시글 목록(userid, userimg, articleidx, articlethumbnail)

    @Autowired
    UserService userService;


    @Autowired
    ArticleService articleService;

    @PostMapping("/user")
    public ResponseEntity<?> searchUserByKeyword(@RequestBody String keyword){
        List<SmallProfileResponseDto> userlist;
        try{
            userlist = userService.getuserlistbykeyword(keyword);
        }catch(Exception e){
            return ResponseEntity.status(200).body(MessageBody.of(true, "글 불러오기 성공"));
    
        }
        return ResponseEntity.status(200).body(UserSearchResponseDto.of(userlist));
    }

    @PostMapping("/hash")
    public ResponseEntity<?> searchArticleBysearchHashByKeyword(@RequestBody String keyword){
        List<Article> articlelist;
        try{
            articlelist = articleService.getarticlelistbykeyword(keyword);
        }catch(Exception e){
            return ResponseEntity.status(200).body(MessageBody.of(true, "글 불러오기 성공"));
    
        }
        return ResponseEntity.status(200).body(ArticleSearchResponseDto.of(articlelist));
    }
}
