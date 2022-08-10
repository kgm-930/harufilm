package com.ssafy.harufilm.fcm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ssafy.harufilm.fcm.NotificationService;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class FcmController {
    public static ResponseEntity<String> FCMMessaging(String to, String title, String message) throws JSONException {
        JSONObject body = new JSONObject();
        JSONObject notification = new JSONObject();
        JSONObject data = new JSONObject();

        //body.put("to", to);
        body.put("to", "dNiV1yt_SvGFZZy49fKRnY:APA91bFAIvXxbCwjTbTZtDr7o2bQKzwzoE4BxCfUx7RwvekRNiROg2cK2aJs_HsyedPlDp2Q7HqTuch-nnQnmC-Bndij8JtXwqv_UWKKftbdMTy9jjQRHVFBWH3YVwAcio2TOkqeHEZF");
        body.put("priority", "high");

        notification.put("title", title);
        notification.put("body", message);

        data.put("title", title);
        data.put("message", message);

        body.put("notification", notification);
        body.put("data", data);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(String.valueOf(body),headers);

        CompletableFuture<String> pushNotification = NotificationService.send(request);
        CompletableFuture.allOf(pushNotification).join();

        try {
            String response = pushNotification.get();

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>("ERROR", HttpStatus.BAD_REQUEST);
    }
}
