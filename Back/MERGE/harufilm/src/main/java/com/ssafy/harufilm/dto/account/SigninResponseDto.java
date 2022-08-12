package com.ssafy.harufilm.dto.account;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SigninResponseDto {

    private int userpid;

    private String token;

    private Boolean articlecheck;

    private String message;

    public static SigninResponseDto of(String message, Integer userpid, String token, Boolean articlecheck) {
        SigninResponseDto body = new SigninResponseDto();
        body.setUserpid(userpid);
        body.setToken(token);
        body.setMessage(message);
        body.setArticlecheck(articlecheck);
        return body;
    }
}
