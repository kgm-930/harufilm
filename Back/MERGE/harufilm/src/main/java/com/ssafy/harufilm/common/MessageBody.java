package com.ssafy.harufilm.common;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
// 정상 작동한 응답 메세지
public class MessageBody {

    String message = null;

    boolean success;

    public static MessageBody of(Boolean success, String message) {
        MessageBody res = new MessageBody();
        res.setMessage(message);
        res.setSuccess(success);
        return res;
    }
}
