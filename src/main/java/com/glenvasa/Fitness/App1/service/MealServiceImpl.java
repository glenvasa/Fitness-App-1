package com.glenvasa.Fitness.App1.service;

import com.glenvasa.Fitness.App1.dto.MealDto;
import com.glenvasa.Fitness.App1.model.Meal;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class MealServiceImpl implements MealService{
    @Override
    public Meal save(Principal principal) {
        return null;
    }

    @Override
    public void update(MealDto mealDto, Meal meal) {

    }

    @Override
    public Meal save(MealDto mealDto, Principal principal) {
        return null;
    }

    @Override
    public List<Meal> loadMeals() {
        return null;
    }
}
