package com.glenvasa.Fitness.App1.service;

import com.glenvasa.Fitness.App1.dto.WorkoutDto;
import com.glenvasa.Fitness.App1.model.Exercise;
import com.glenvasa.Fitness.App1.model.Sets;
import com.glenvasa.Fitness.App1.model.User;
import com.glenvasa.Fitness.App1.model.Workout;
import com.glenvasa.Fitness.App1.repository.SetsRepository;
import com.glenvasa.Fitness.App1.repository.UserRepository;
import com.glenvasa.Fitness.App1.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;

@Service
public class WorkoutServiceImpl implements WorkoutService {


    private final SetsRepository setsRepository;
    private final WorkoutRepository workoutRepository;
    private final UserService userService;

    @Autowired
    public WorkoutServiceImpl(SetsRepository setsRepository, WorkoutRepository workoutRepository, UserService userService) {
        this.setsRepository = setsRepository;
        this.workoutRepository = workoutRepository;
        this.userService = userService;
    }

    // to create workout before adding sets, other data; just user object and auto created id
    @Override
    public Workout save(Principal principal) {
        String email = principal.getName();
        User user = userService.loadUserByEmail(email);
        Workout workout = new Workout(user);
        return workoutRepository.save(workout);
    }

    @Override
    public Workout save(WorkoutDto workoutDto, Principal principal) {
        return null;
    }

    // save workout after adding name,duration, date info AFTER creating/adding all Sets
    public Workout update(WorkoutDto workoutDto, Workout workout) {



        return null;

    }



    @Override
    public List<Workout> loadWorkouts() {
        return workoutRepository.findAll();
    }
}

