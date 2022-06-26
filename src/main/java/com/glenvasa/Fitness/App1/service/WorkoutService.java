package com.glenvasa.Fitness.App1.service;

import com.glenvasa.Fitness.App1.dto.WorkoutDto;
import com.glenvasa.Fitness.App1.model.Workout;
import org.springframework.stereotype.Service;
import java.security.Principal;
import java.util.List;

@Service
public interface WorkoutService {

    Workout save(Principal principal);
    void update(WorkoutDto workoutDto, Workout workout);
    List<Workout> loadWorkouts();
    void deleteWorkout(Long workoutId);
}