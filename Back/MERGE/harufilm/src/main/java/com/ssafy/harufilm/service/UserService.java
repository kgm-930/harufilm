package com.ssafy.harufilm.service;

import com.ssafy.harufilm.dto.account.SignupRequestDto;
import com.ssafy.harufilm.entity.User;

public interface UserService {

    User getuserbyId(String userid);

    User getuserbyPid(int userpid);

    User userNewSave(SignupRequestDto signupRequestDto);


}
