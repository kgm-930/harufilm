package com.ssafy.harufilm.service.subscribe;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.harufilm.dto.account.SmallProfileResponseDto;
import com.ssafy.harufilm.dto.subscribe.SubscribeRequestDto;
import com.ssafy.harufilm.entity.Subscribe;
import com.ssafy.harufilm.repository.subscribe.SubscribeRepository;
import com.ssafy.harufilm.repository.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubscribeServiceImpl implements SubscribeService {

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
        if (sub != null) {
            subscribeRepository.delete(sub);
        }
    }

    @Override
    public List<SmallProfileResponseDto> followerList(int userpid) {
        List<SmallProfileResponseDto> list = subscribeRepository.followList(userpid);
        if (list != null) {
            return list;
        } else
            return null;
    }

    @Override
    public List<SmallProfileResponseDto> followList(int userpid) {
        List<SmallProfileResponseDto> list = subscribeRepository.followerList(userpid);
        if (list != null) {
            return list;
        } else
            return null;
    }

    @Override
    public Boolean followBoolean(int subfrom, int subto) {
        Subscribe sub = subscribeRepository.findBySubfromAndSubto(subfrom, subto);
        if(sub!=null){
            return true;
        }else{
            return false;
        }
    }

}
