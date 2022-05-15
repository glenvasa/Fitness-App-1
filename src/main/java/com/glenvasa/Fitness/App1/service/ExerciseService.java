package com.glenvasa.Fitness.App1.service;

import com.glenvasa.Fitness.App1.dto.ExerciseDto;
import com.glenvasa.Fitness.App1.model.Exercise;

import java.util.List;

public interface ExerciseService {
    Exercise save(ExerciseDto exerciseDto);
    List<Exercise> loadExercises();
}
