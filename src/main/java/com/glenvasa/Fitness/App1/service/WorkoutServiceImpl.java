package com.glenvasa.Fitness.App1.service;

import com.glenvasa.Fitness.App1.dto.WorkoutDto;
import com.glenvasa.Fitness.App1.model.*;
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


//    private final SetsRepository setsRepository;
    private final WorkoutRepository workoutRepository;
    private final UserService userService;

    @Autowired
    public WorkoutServiceImpl(WorkoutRepository workoutRepository, UserService userService) {
//        this.setsRepository = setsRepository;
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

    // don't believe using this method; will likely delete
    @Override
    public Workout save(WorkoutDto workoutDto, Principal principal) {
        return null;
    }

    // save workout after adding name,duration, date info AFTER creating/adding all Sets
    @Override
    public void update(WorkoutDto workoutDto, Workout workout) {
//        User user = workout.getUser();
//        Workout updatedWorkout = new Workout(workoutDto.getWorkoutName(), workoutDto.getDateOfWorkout(),
//               workoutDto.getDuration(), user, workout.getSets());


                workoutRepository.updateWorkoutById(workoutDto.getDateOfWorkout(), workoutDto.getDuration(),
                workoutDto.getWorkoutName(), workout.getId());

    }



//    @Override
//    public PersonalRecords getPersonalRecords(Principal principal){
//        String email = principal.getName();
//        User user = userService.loadUserByEmail(email);
//        return workoutRepository.findPersonalRecord(user.getId());
//    }


    @Override
    public List<Workout> loadWorkouts() {
        return workoutRepository.findAll();
    }
}

