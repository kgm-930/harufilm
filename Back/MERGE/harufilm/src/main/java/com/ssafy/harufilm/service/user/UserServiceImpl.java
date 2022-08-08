package com.ssafy.harufilm.service.user;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.harufilm.dto.account.SignupRequestDto;
import com.ssafy.harufilm.dto.profile.ModifyRequestDto;
import com.ssafy.harufilm.entity.User;
import com.ssafy.harufilm.repository.user.UserRepository;

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
        return userRepository.findByUserpid(userpid);
    }

    @Transactional
    @Override
    public User userNewSave(SignupRequestDto signupRequestDto) {

        String userimg = "baseimg.png";

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

    @Override
    public void modifyprofile(ModifyRequestDto modifyRequestDto) throws IllegalStateException, IOException {

        User user = userRepository.findByUserpid(modifyRequestDto.getUserpid());

        // [1] 새 설명이 있을경우
        if (modifyRequestDto.getUserdesc() != null)
            user.setUserdesc(modifyRequestDto.getUserdesc());

        // [2] 새 유저 이미지 파일이 들어 있을 경우
        if (modifyRequestDto.getUserimg() != null) {
            MultipartFile newimg = modifyRequestDto.getUserimg();
            String imgpath = "/var/opt/upload/profile/";

            // 1. profile_userimg.png 가 아닌 다른 파일을 가리킬 경우 해당 파일 삭제
            String curimg = user.getUserimg();

            if (!curimg.equals("baseimg.png")) {
                File curfile = new File(imgpath + curimg);
                try {
                    curfile.delete();
                } catch (Exception e) {
                }
            }

            // 2. DB업데이트 반드시 이미지 이름으로 하되 pid를 붙여서 저장 ex) pid_이미지이름.png
            String updateimg = Integer.toString(user.getUserpid()) + "_" + newimg.getOriginalFilename();
            user.setUserimg(updateimg);

            // 3. 해당 파일 서버에 저장
            File updatefile = new File(imgpath + updateimg);
            newimg.transferTo(updatefile);
        }

        // [3] 새 유저 이름이 있을경우
        if (modifyRequestDto.getUsername() != null)
            user.setUsername(modifyRequestDto.getUsername());

        userRepository.save(user);

    }
}
