package com.ssafy.harufilm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.harufilm.common.ErrorResponseBody;
import com.ssafy.harufilm.common.MessageBody;
import com.ssafy.harufilm.dto.article.ArticleShowRequestDto;
import com.ssafy.harufilm.dto.likey.LikeyRequestDto;
import com.ssafy.harufilm.service.likey.LikeyService;
 
@RestController
@RequestMapping("/api/likey")
public class LikeyController {
    
    @Autowired
    private LikeyService likeyService;
    //좋아요 생성
    @PostMapping("/create")
    public ResponseEntity<?> setlikey(@RequestBody LikeyRequestDto likeyRequestDto){
        try{
            likeyService.likeySave(likeyRequestDto);
        }catch(Exception e){
            return ResponseEntity.status(500).body(ErrorResponseBody.of(500, false,
            "Interanl Server Error, 좋아요 실패"));
        }
        return ResponseEntity.status(200).body(MessageBody.of(true, "좋아요"));
    }
    //좋아요 취소
    @PostMapping("/delete")
    public ResponseEntity<?> deletelikey(@RequestBody LikeyRequestDto likeyRequestDto){
        try{
            likeyService.likeyDelete(likeyRequestDto);
        }catch(Exception e){
            return ResponseEntity.status(500).body(ErrorResponseBody.of(500, false,
            "Interanl Server Error, 좋아요 취소 실패"));
        }
        return ResponseEntity.status(200).body(MessageBody.of(true, "좋아요 취소"));
    }
    
}