package com.ssafy.harufilm.fcm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ssafy.harufilm.fcm.NotificationService;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class FcmController {
    public static ResponseEntity<String> FCMMessaging(String to, String title, String message)throws JSONException{
         JSONObject body = new JSONObject();

         body.put("to","dNiV1yt_SvGFZZy49fKRnY:APA91bFAIvXxbCwjTbTZtDr7o2bQKzwzoE4BxCfUx7RwvekRNiROg2cK2aJs_HsyedPlDp2Q7HqTuch-nnQnmC-Bndij8JtXwqv_UWKKftbdMTy9jjQRHVFBWH3YVwAcio2TOkqeHEZF");
         body.put("priority","high");

         JSONObject notification = new JSONObject();

         notification.put("title","노타");
         notification.put("body","노바");

         JSONObject data = new JSONObject();

         data.put("title","데바");
         data.put("message","데메");

         body.put("notification",notification);
         body.put("data",data);

         HttpEntity<String> request = new HttpEntity<>(body.toString());

         CompletableFuture<String> pushNotification = NotificationService.send(request);
         CompletableFuture.allOf(pushNotification).join();

         try {
             String response = pushNotification.get();

             return new ResponseEntity<>(response, HttpStatus.OK);
         }catch (InterruptedException e){
             e.printStackTrace();
         }catch (ExecutionException e){
             e.printStackTrace();
         }

         return new ResponseEntity<>("ERROR", HttpStatus.BAD_REQUEST);
     }
}
