package com.glenvasa.Fitness.App1.service;

import com.glenvasa.Fitness.App1.dto.UserRegistrationDto;
import com.glenvasa.Fitness.App1.exception.UserAlreadyExistsException;
import com.glenvasa.Fitness.App1.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.security.Principal;

// UserDetailsService is part of Spring Security used to protect User credentials
public interface UserService extends UserDetailsService {

    User save(UserRegistrationDto registrationDto) throws UserAlreadyExistsException;
    User loadUserByEmail(String email);

    void update(UserRegistrationDto userRegistrationDto, Principal principal);
    void delete(Principal principal);
}
