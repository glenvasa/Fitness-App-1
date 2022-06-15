package com.glenvasa.Fitness.App1.service;

import com.glenvasa.Fitness.App1.dto.HealthProfileDto;
import com.glenvasa.Fitness.App1.model.HealthProfile;
import com.glenvasa.Fitness.App1.model.User;
import com.glenvasa.Fitness.App1.repository.HealthProfileRepository;
import com.glenvasa.Fitness.App1.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;

@Service
public class HealthProfileServiceImpl implements HealthProfileService{

    private HealthProfileRepository healthProfileRepository;
    private UserRepository userRepository;

    public HealthProfileServiceImpl(HealthProfileRepository healthProfileRepository, UserRepository userRepository) {
        this.healthProfileRepository = healthProfileRepository;
        this.userRepository = userRepository;
    }

    @Override
    public HealthProfile save(HealthProfileDto healthProfileDto, Principal principal) {
        String email = principal.getName();
        User user = userRepository.findByEmail(email);
        LocalDate today = LocalDate.now();
        HealthProfile healthProfile = new HealthProfile(today, healthProfileDto.getWeight(), healthProfileDto.getExerciseLevel(), healthProfileDto.getMaintenanceCalories(), user);
        return healthProfileRepository.save(healthProfile);
    }
}
