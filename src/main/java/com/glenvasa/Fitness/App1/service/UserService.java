package com.glenvasa.Fitness.App1.service;

import com.glenvasa.Fitness.App1.dto.UserRegistrationDto;
import com.glenvasa.Fitness.App1.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService { // UserDetailsService is part of Spring Security used to
    // protect User credentials

    User save(UserRegistrationDto registrationDto);
    User loadUserByEmail(String email);

}
