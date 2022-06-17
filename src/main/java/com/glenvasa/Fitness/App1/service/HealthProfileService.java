package com.glenvasa.Fitness.App1.service;

import com.glenvasa.Fitness.App1.dto.HealthProfileDto;
import com.glenvasa.Fitness.App1.dto.UserRegistrationDto;
import com.glenvasa.Fitness.App1.model.HealthProfile;
import com.glenvasa.Fitness.App1.model.User;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

public interface HealthProfileService {
    HealthProfile save(HealthProfileDto healthProfileDto, Principal principal);


    List<HealthProfile> findTodayProfiles(Principal principal, LocalDate today);
}
