package com.glenvasa.Fitness.App1.textUser;

import com.glenvasa.Fitness.App1.dto.SmsRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/v1/sms")
public class SmsController {
    private final SmsService service;

    @Autowired
    public SmsController(SmsService service) {
        this.service = service;
    }

    @PostMapping         // add @Valid before @RequestBody to activate @NotBlank annotations for SMSRequest fields
    public String sendSms(SmsRequestDto smsRequestDto){
        service.sendSms(smsRequestDto);
        return "redirect:/profile";
    }



}

