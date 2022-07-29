package com.ssafy.harufilm.service;

import java.io.IOException;

import com.ssafy.harufilm.dto.account.SignupRequestDto;
import com.ssafy.harufilm.entity.User;

public interface UserService {

    User getuserbyId(String userid);

    User userNewSave(SignupRequestDto signupRequestDto) throws IOException;
}
