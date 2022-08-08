package com.ssafy.harufilm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.harufilm.common.ErrorResponseBody;
import com.ssafy.harufilm.common.MessageBody;
import com.ssafy.harufilm.entity.User;
import com.ssafy.harufilm.service.user.UserService;

@RestController
@RequestMapping("/api/account")
public class PasswordController {
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @PostMapping("/findpw")
    public ResponseEntity<?> pwqchk(@RequestBody String userid, int Q, String A) {
        User user = new User();
        try {
            user = userService.getuserbyId(userid);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ErrorResponseBody.of(500, false,
                    "Interanl Server Error, 서버 에러."));
        }
        if (user == null) {
            return ResponseEntity.status(400).body(ErrorResponseBody.of(400, false, "없는 아이디"));
        }
        if(user.getUserpwq() == Q && user.getUserpwa() == A){
        return ResponseEntity.status(200).body(MessageBody.of(true, "비밀 번호 질문 일치"));
    }
        return ResponseEntity.status(200).body(MessageBody.of(false, "비밀 번호 질문 불일치"));
    }

    @PostMapping("/changepw")
    public ResponseEntity<?> pwqchk(@RequestBody String userid, String userpw, String usernewpw) {
        User user = userService.getuserbyId(userid);
        user.setUserpassword(passwordEncoder.encode(usernewpw));
        return ResponseEntity.status(200).body(MessageBody.of(true, "비밀 번호 변경"));
    }
}
