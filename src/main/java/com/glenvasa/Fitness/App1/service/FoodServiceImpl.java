package com.glenvasa.Fitness.App1.service;

import com.glenvasa.Fitness.App1.dto.FoodDto;
import com.glenvasa.Fitness.App1.model.Food;
import com.glenvasa.Fitness.App1.model.Servings;
import com.glenvasa.Fitness.App1.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class FoodServiceImpl implements FoodService{

    private final FoodRepository foodRepository;

    @Autowired
    public FoodServiceImpl(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }


    @Override
    public Food save(FoodDto foodDto) {
        Food food = new Food(foodDto.getName(), foodDto.getServingSize(), foodDto.getCalories(), foodDto.getFat(), foodDto.getCarbs(),
                foodDto.getProtein(), new HashSet<Servings>());
        return foodRepository.save(food);
    }

    @Override
    public List<Food> loadFood() {
        return foodRepository.findAll();
    }
}
