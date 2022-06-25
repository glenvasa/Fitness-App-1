package com.glenvasa.Fitness.App1.service;

import com.glenvasa.Fitness.App1.dto.UserRegistrationDto;
import com.glenvasa.Fitness.App1.exception.UserAlreadyExistsException;
import com.glenvasa.Fitness.App1.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.security.Principal;


public interface UserService extends UserDetailsService { // UserDetailsService is part of Spring Security used to
    // protect User credentials

    User save(UserRegistrationDto registrationDto) throws UserAlreadyExistsException;
    User loadUserByEmail(String email);

    void update(UserRegistrationDto userRegistrationDto, Principal principal);
//    void updateUserMaintCals(Integer maintCals, Principal principal);
    void delete(Principal principal);
}
