package com.glenvasa.Fitness.App1.service;

import com.glenvasa.Fitness.App1.dto.ExerciseDto;
import com.glenvasa.Fitness.App1.dto.SetsDto;
import com.glenvasa.Fitness.App1.model.Exercise;
import com.glenvasa.Fitness.App1.model.ExerciseCategory;
import com.glenvasa.Fitness.App1.model.Sets;
import com.glenvasa.Fitness.App1.model.Workout;
import com.glenvasa.Fitness.App1.repository.ExerciseCategoryRepository;
import com.glenvasa.Fitness.App1.repository.ExerciseRepository;
import com.glenvasa.Fitness.App1.repository.SetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class SetsServiceImpl implements SetsService {


    private final SetsRepository setsRepository;
    private final ExerciseRepository exerciseRepository;

    @Autowired
    public SetsServiceImpl(SetsRepository setsRepository, ExerciseRepository exerciseRepository) {
        this.setsRepository = setsRepository;
        this.exerciseRepository = exerciseRepository;
    }


    @Override
    public Sets save(SetsDto setsDto) {
        Exercise exercise = exerciseRepository.findByName(setsDto.getExercise());
//        System.out.println(exercise.toString());
        // Need to use Workout Id from user workout / retrieve that Workout object instead of creating new one
//        Workout workout = workoutRepository.findById(
        Sets sets = new Sets(setsDto.getRepetitions(), setsDto.getWeight(), exercise, new Workout());

        return setsRepository.save(sets);
    }



    @Override
    public List<Sets> loadSets() {
        return setsRepository.findAll();
    }
}

