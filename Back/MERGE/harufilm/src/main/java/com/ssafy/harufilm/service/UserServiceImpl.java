package com.ssafy.harufilm.service;

import java.io.IOException;

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

    @Transactional
    @Override
    public User userNewSave(SignupRequestDto signupRequestDto) throws IOException {

        User user = User.builder()
                .userid(signupRequestDto.getUserid())
                .userpassword(signupRequestDto.getUserpassword())
                .roles("USER")
                .build();

        // TODO Auto-generated method stub
        return userRepository.save(user);
    }

    @Override
    public User getuserbyId(String userid) {

        return userRepository.findByUserid(userid).orElse(null);
    }

}
