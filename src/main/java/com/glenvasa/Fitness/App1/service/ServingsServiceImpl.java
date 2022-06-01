package com.glenvasa.Fitness.App1.service;

import com.glenvasa.Fitness.App1.dto.ServingsDto;
import com.glenvasa.Fitness.App1.model.Food;
import com.glenvasa.Fitness.App1.model.Meal;
import com.glenvasa.Fitness.App1.model.Servings;
import com.glenvasa.Fitness.App1.model.Sets;
import com.glenvasa.Fitness.App1.repository.FoodRepository;
import com.glenvasa.Fitness.App1.repository.MealRepository;
import com.glenvasa.Fitness.App1.repository.ServingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServingsServiceImpl implements ServingsService {
    private final ServingsRepository servingsRepository;
    private final FoodRepository foodRepository;
    private final MealRepository mealRepository;

    @Autowired
    public ServingsServiceImpl(ServingsRepository servingsRepository, FoodRepository foodRepository,
                               MealRepository mealRepository) {
        this.servingsRepository = servingsRepository;
        this.foodRepository = foodRepository;
        this.mealRepository = mealRepository;
    }


    @Override

    public Servings save(ServingsDto servingsDto) {
        Food food = foodRepository.findByName(servingsDto.getFood());
        // Need to use Meal Id from user workout / retrieve existing Meal object instead of creating new one
        Meal meal = mealRepository.findTopByOrderByIdDesc();
        Servings servings = new Servings(servingsDto.getNumber(), meal, food);

        return servingsRepository.save(servings);
    }

    @Override
    public List<Servings> loadServings() {
        return servingsRepository.findAll();
    }

}