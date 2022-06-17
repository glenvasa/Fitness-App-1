package com.glenvasa.Fitness.App1.textUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/sms")
public class SmsController {
    private final SmsService service;

    @Autowired
    public SmsController(SmsService service) {
        this.service = service;
    }

    @PostMapping         // add @Valid before @RequestBody to activate @NotBlank annotations for SMSRequest fields
    public void sendSms(@RequestBody SmsRequest smsRequest){
        service.sendSms(smsRequest);
    }



}

