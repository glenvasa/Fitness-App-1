package com.glenvasa.Fitness.App1.service;

import com.glenvasa.Fitness.App1.dto.ExerciseDto;
import com.glenvasa.Fitness.App1.dto.SetsDto;
import com.glenvasa.Fitness.App1.model.*;
import com.glenvasa.Fitness.App1.repository.ExerciseCategoryRepository;
import com.glenvasa.Fitness.App1.repository.ExerciseRepository;
import com.glenvasa.Fitness.App1.repository.SetsRepository;
import com.glenvasa.Fitness.App1.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;

@Service
public class SetsServiceImpl implements SetsService {


    private final SetsRepository setsRepository;
    private final ExerciseRepository exerciseRepository;
    private final WorkoutRepository workoutRepository;
    private final UserService userService;

    @Autowired
    public SetsServiceImpl(SetsRepository setsRepository, ExerciseRepository exerciseRepository,
                           WorkoutRepository workoutRepository, UserService userService) {
        this.setsRepository = setsRepository;
        this.exerciseRepository = exerciseRepository;
        this.workoutRepository = workoutRepository;
        this.userService = userService;
    }


    @Override
    //creates a Sets with newly created Workout Id
    public Sets save(SetsDto setsDto) {
        Exercise exercise = exerciseRepository.findByName(setsDto.getExercise());
//        System.out.println(exercise.toString());
        // Need to use Workout Id from user workout / retrieve that Workout object instead of creating new one
        Workout workout = workoutRepository.findTopByOrderByIdDesc();
        Sets sets = new Sets(setsDto.getRepetitions(), setsDto.getWeight(), exercise, workout);

        return setsRepository.save(sets);
    }



    @Override
    public List<Sets> loadSets() {
        return setsRepository.findAll();
    }

    @Override
    public List<Sets> loadSetsByUserId(Principal principal) {
        String email = principal.getName();
        User user = userService.loadUserByEmail(email);
        return setsRepository.findAllByUserId(user.getId());
    }
}

