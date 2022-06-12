package com.glenvasa.Fitness.App1.service;

import com.glenvasa.Fitness.App1.dto.ExerciseCategoryDto;
import com.glenvasa.Fitness.App1.dto.FoodCategoryDto;
import com.glenvasa.Fitness.App1.model.ExerciseCategory;
import com.glenvasa.Fitness.App1.model.FoodCategory;

import java.util.List;

public interface FoodCategoryService {
    FoodCategory save(FoodCategoryDto categoryDto);
    List<FoodCategory> loadFoodCategories();

}
