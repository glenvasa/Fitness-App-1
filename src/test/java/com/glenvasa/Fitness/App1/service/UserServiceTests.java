package com.glenvasa.Fitness.App1.service;

import com.glenvasa.Fitness.App1.dto.UserRegistrationDto;
import com.glenvasa.Fitness.App1.exception.UserAlreadyExistsException;
import com.glenvasa.Fitness.App1.model.Meal;
import com.glenvasa.Fitness.App1.model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.security.Principal;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserServiceTests {


    @Autowired
    private UserServiceImpl userService;

    @Test // passes
    public void testSaveUser() throws UserAlreadyExistsException {

        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        userRegistrationDto.setEmail("testemail@test.com");
        userRegistrationDto.setPassword("12345");
        User savedUser = userService.save(userRegistrationDto);
        Assertions.assertThat(savedUser.getId()).isNotNull();
    }

}


