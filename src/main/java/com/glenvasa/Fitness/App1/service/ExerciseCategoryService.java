package com.glenvasa.Fitness.App1.service;

import com.glenvasa.Fitness.App1.dto.ExerciseCategoryDto;
import com.glenvasa.Fitness.App1.dto.UserRegistrationDto;
import com.glenvasa.Fitness.App1.model.ExerciseCategory;

import java.util.List;


public interface ExerciseCategoryService {
    ExerciseCategory save(ExerciseCategoryDto categoryDto);
    List<ExerciseCategory> loadExerciseCategories();

}
