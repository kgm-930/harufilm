package com.ssafy.harufilm.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.harufilm.dto.account.SignupRequestDto;
import com.ssafy.harufilm.entity.User;
import com.ssafy.harufilm.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getuserbyId(String userid) {
        return userRepository.findByUserid(userid).orElse(null);
    }

    @Override
    public User getuserbyPid(int userpid) {
        return userRepository.findByUserpid(userpid).orElse(null);
    }


    @Transactional
    @Override
    public User userNewSave(SignupRequestDto signupRequestDto) {
        
        String userimg = "/var/opt/upload/baseimg.jpg";
        
        User user = User.builder()
                .userid(signupRequestDto.getUserid())
                .userpassword(signupRequestDto.getUserpassword())
                .username(signupRequestDto.getUsername())
                .userimg(userimg)
                .userdesc("")
                .userpwq(signupRequestDto.getUserpwq())
                .userpwa(signupRequestDto.getUserpwa())
                .roles("USER")
                .build();

        return userRepository.save(user);
    }
}
