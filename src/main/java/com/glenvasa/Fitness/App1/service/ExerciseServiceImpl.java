package com.glenvasa.Fitness.App1.service;

import com.glenvasa.Fitness.App1.dto.ExerciseDto;
import com.glenvasa.Fitness.App1.model.Exercise;
import com.glenvasa.Fitness.App1.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseServiceImpl implements ExerciseService {


    private final ExerciseRepository exerciseRepository;

    @Autowired
    public ExerciseServiceImpl(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }


    @Override
    public Exercise save(ExerciseDto exerciseDto) {
        Exercise exercise = new Exercise(exerciseDto.getName(), exerciseDto.getDescription(), exerciseDto.getExerciseCategory());
        return exerciseRepository.save(exercise);
    }

    @Override
    public List<Exercise> loadExercises() {
        return exerciseRepository.findAll();
    }
}
