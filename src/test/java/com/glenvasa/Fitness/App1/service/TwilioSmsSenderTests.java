package com.glenvasa.Fitness.App1.service;

import com.glenvasa.Fitness.App1.textUser.SmsRequest;
import com.glenvasa.Fitness.App1.textUser.TwilioSmsSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.aspectj.bridge.MessageUtil.fail;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class TwilioSmsSenderTests {


    @Autowired
    private TwilioSmsSender smsSender;

    @Test // test passes; catch block/fail not reached AND I actually received the text message.
    public void testSendSms() {
        try {
            SmsRequest smsRequest = new SmsRequest("7814678117", "Hello from SmsServiceTests.");
            smsSender.sendSms(smsRequest);
        } catch (IllegalArgumentException e) {
            fail("Should not have thrown any exception since valid phone Number and message inputted.");
        }

    }
}