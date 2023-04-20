package com.example.fcm.controller;

import com.example.fcm.dto.FcmTokenDto;
import com.example.fcm.dto.ResponseDto;
import com.example.fcm.service.FCMNotificationSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@RequiredArgsConstructor
@Controller
public class FCMController {

    private final FCMNotificationSender fcmNotificationSender;

    @GetMapping("/")
    public String fcm() {
        return "index";
    }

    @ResponseBody
    @PostMapping("api/fcm")
    public ResponseEntity<ResponseDto> sendNotification(@RequestBody FcmTokenDto dto) {
        log.info("token: {}", dto.getToken());
        fcmNotificationSender.sendNotification(dto.getToken(), "TestTitle", "TestBody");
        return ResponseEntity.ok().body(new ResponseDto("OK"));
    }
}
