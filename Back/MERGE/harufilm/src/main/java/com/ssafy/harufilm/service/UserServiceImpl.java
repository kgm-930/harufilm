package com.ssafy.harufilm.service;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
        User user = User.builder()
                .userid(signupRequestDto.getUserid())
                .userpassword(signupRequestDto.getUserpassword())
                .roles("USER")
                .build();

        return userRepository.save(user);
    }
}
