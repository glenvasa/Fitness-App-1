package com.glenvasa.Fitness.App1.service;

import com.glenvasa.Fitness.App1.dto.MealDto;
import com.glenvasa.Fitness.App1.dto.WorkoutDto;
import com.glenvasa.Fitness.App1.model.Meal;
import com.glenvasa.Fitness.App1.model.Workout;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public interface MealService {
    Meal save(Principal principal);
    void update(MealDto mealDto, Meal meal);
    Meal save(MealDto mealDto, Principal principal);
    List<Meal> loadMeals();

    void deleteMeal(Long mealId);
}
