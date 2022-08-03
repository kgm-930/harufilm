package com.ssafy.harufilm.service.subscribe;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.harufilm.dto.subscribe.SubscribeRequestDto;
import com.ssafy.harufilm.dto.subscribe.SubscribeUserDto;
import com.ssafy.harufilm.entity.Subscribe;
import com.ssafy.harufilm.entity.User;
import com.ssafy.harufilm.repository.UserRepository;
import com.ssafy.harufilm.repository.subscribe.SubscribeRepository;

public class SubscribeServiceImpl implements SubscribeService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    SubscribeRepository subscribeRepository;

    @Override
    public Subscribe subscribeSave(SubscribeRequestDto subscribeRequestDto) {
        Subscribe sub = Subscribe.builder()
            .subfrom(subscribeRequestDto.getSubfrom())
            .subto(subscribeRequestDto.getSubto())
            .build();

            return subscribeRepository.save(sub);
    }

    @Override
    public void subscribeDelete(SubscribeRequestDto subscribeRequestDto) {
        Subscribe sub = subscribeRepository.findBySubfromAndSubto(
            subscribeRequestDto.getSubfrom(),
            subscribeRequestDto.getSubto());
        if(sub!=null){
            subscribeRepository.delete(sub);
        }
    }

    @Override
    public List<SubscribeUserDto> subscribeFollowing(int userpid) {
        List<SubscribeUserDto> userlist;
        User user;

        subscribeRepository.findAllBySubto(userpid);
        return null;
    }

    @Override
    public List<SubscribeUserDto> subscribeFollowed(int userpid) {
        // TODO Auto-generated method stub
        return null;
    }
}
