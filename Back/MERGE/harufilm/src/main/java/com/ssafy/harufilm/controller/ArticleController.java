package com.ssafy.harufilm.controller;

import org.apache.catalina.connector.Response;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.harufilm.common.MessageBody;
import com.ssafy.harufilm.dto.article.ArticleRequestDto;
import com.ssafy.harufilm.service.article.ArticleService;

@RestController
@RequestMapping("/api/article")
public class ArticleController {
    
    @Autowired
    private ArticleService articleService;

    //아티클 삽입
    //아티클 삭제
    //아티클 리스트
    //아티클 리

}
