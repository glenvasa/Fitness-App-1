package com.glenvasa.Fitness.App1.service;

import com.glenvasa.Fitness.App1.dto.UserRegistrationDto;
import com.glenvasa.Fitness.App1.model.User;
import com.glenvasa.Fitness.App1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserRegistrationDto registrationDto) {
        // need to implement BCryptPasswordEncoder later
        User user = new User(registrationDto.getFirstName(), registrationDto.getLastName(),
                registrationDto.getStreetAddress(), registrationDto.getApartment(), registrationDto.getCity(),
                registrationDto.getState(), registrationDto.getZipCode(), registrationDto.getPhone1(),
                registrationDto.getPhone2(), registrationDto.getEmail(), registrationDto.getPassword(),
                registrationDto.getHeight(), registrationDto.getDateOfBirth());
        return userRepository.save(user);
    }

}
