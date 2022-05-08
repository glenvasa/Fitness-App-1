package com.glenvasa.Fitness.App1.service;

import com.glenvasa.Fitness.App1.dto.UserRegistrationDto;
import com.glenvasa.Fitness.App1.model.User;

public interface UserService {

    User save(UserRegistrationDto registrationDto);

}
