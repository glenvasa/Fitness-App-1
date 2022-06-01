package com.glenvasa.Fitness.App1.service;

import com.glenvasa.Fitness.App1.dto.ExerciseDto;
import com.glenvasa.Fitness.App1.dto.FoodDto;
import com.glenvasa.Fitness.App1.model.Exercise;
import com.glenvasa.Fitness.App1.model.Food;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FoodService {
    Food save(FoodDto foodDto);
    List<Food> loadFood();
}