package com.glenvasa.Fitness.App1.service;

import com.glenvasa.Fitness.App1.dto.SetsDto;
import com.glenvasa.Fitness.App1.dto.WorkoutDto;
import com.glenvasa.Fitness.App1.model.Sets;
import com.glenvasa.Fitness.App1.model.Workout;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public interface WorkoutService {
    Workout save(Principal principal);
    Workout save(WorkoutDto workoutDto, Principal principal);
    List<Workout> loadWorkouts();
}