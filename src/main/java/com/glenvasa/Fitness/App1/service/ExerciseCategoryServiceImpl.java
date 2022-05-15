package com.glenvasa.Fitness.App1.service;

import com.glenvasa.Fitness.App1.dto.ExerciseCategoryDto;
import com.glenvasa.Fitness.App1.model.ExerciseCategory;
import com.glenvasa.Fitness.App1.model.Role;
import com.glenvasa.Fitness.App1.model.User;
import com.glenvasa.Fitness.App1.repository.ExerciseCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ExerciseCategoryServiceImpl implements ExerciseCategoryService{

    private final ExerciseCategoryRepository categoryRepository;

    @Autowired
    public ExerciseCategoryServiceImpl(ExerciseCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public ExerciseCategory save(ExerciseCategoryDto categoryDto) {
        ExerciseCategory exerciseCategory = new ExerciseCategory(categoryDto.getName(), categoryDto.getDescription());
        return categoryRepository.save(exerciseCategory);
    }

    @Override
    public List<ExerciseCategory> loadExerciseCategories() {
        return categoryRepository.findAll();
    }
}