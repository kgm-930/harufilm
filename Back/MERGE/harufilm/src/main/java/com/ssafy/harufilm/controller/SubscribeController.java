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
import com.ssafy.harufilm.dto.subscribe.SubscribeRequestDto;
import com.ssafy.harufilm.service.subscribe.SubscribeService;

@RestController
@RequestMapping("/api/subscribe")
public class SubscribeController {
    
    @Autowired
    private SubscribeService subscribeService;

    @PostMapping("")
    public ResponseEntity<?> setsub(@RequestBody SubscribeRequestDto subscribeRequestDto){
        try{
            subscribeService.subscribeSave(subscribeRequestDto);
        }catch(Exception e){
            return ResponseEntity.status(500).body(ErrorResponseBody.of(500, false,
            "Interanl Server Error, 팔로우 실패"));
        }
        return ResponseEntity.status(200).body(MessageBody.of(true, "팔로우"));
    }

    @DeleteMapping("")
    public ResponseEntity<?> deletesub(@RequestBody SubscribeRequestDto subscribeRequestDto){
        try{
            subscribeService.subscribeDelete(subscribeRequestDto);
        }catch(Exception e){
            return ResponseEntity.status(500).body(ErrorResponseBody.of(500, false,
            "Interanl Server Error, 팔로우 취소 실패"));
        }
        return ResponseEntity.status(200).body(MessageBody.of(true, "팔로우 취소"));
    
    }
}
