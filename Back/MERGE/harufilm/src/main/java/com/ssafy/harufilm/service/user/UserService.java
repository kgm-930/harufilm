package com.ssafy.harufilm.service.user;

import java.io.IOException;

import com.ssafy.harufilm.dto.account.SignupRequestDto;
import com.ssafy.harufilm.dto.profile.ModifyRequestDto;
import com.ssafy.harufilm.entity.User;

public interface UserService {

    User getuserbyId(String userid);

    User getuserbyPid(int userpid);

    User userNewSave(SignupRequestDto signupRequestDto);

    void modifyprofile(ModifyRequestDto modifyRequestDto) throws IllegalStateException, IOException;

}
