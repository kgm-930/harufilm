package com.ssafy.harufilm.service.subscribe;

import java.util.List;

import com.ssafy.harufilm.dto.subscribe.SubscribeRequestDto;
import com.ssafy.harufilm.dto.subscribe.SubscribeUserDto;
import com.ssafy.harufilm.entity.Subscribe;

public interface SubscribeService{
    Subscribe subscribeSave(SubscribeRequestDto subscribeRequestDto); // 새 팔로우 저장
    void subscribeDelete(SubscribeRequestDto subscribeRequestDto);// 팔로우 삭제

    List<SubscribeUserDto> subscribeFollowing(int userpid);
    List<SubscribeUserDto> subscribeFollowed(int userpid);
}
