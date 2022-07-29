package com.ssafy.harufilm.service;

import com.ssafy.harufilm.entity.User;

public interface UserService {

    User getuserbyId(String userid);

    User getuserbyPid(int userpid);

}
