package com.glenvasa.Fitness.App1.service;

import com.glenvasa.Fitness.App1.dto.FoodDto;
import com.glenvasa.Fitness.App1.model.Food;
import com.glenvasa.Fitness.App1.model.FoodCategory;
import com.glenvasa.Fitness.App1.model.Servings;
import com.glenvasa.Fitness.App1.repository.FoodCategoryRepository;
import com.glenvasa.Fitness.App1.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class FoodServiceImpl implements FoodService{

    private final FoodRepository foodRepository;
    private final FoodCategoryRepository foodCategoryRepository;

    @Autowired
    public FoodServiceImpl(FoodRepository foodRepository, FoodCategoryRepository foodCategoryRepository) {
        this.foodRepository = foodRepository;
        this.foodCategoryRepository = foodCategoryRepository;
    }


    @Override
    public Food save(FoodDto foodDto) {
        FoodCategory foodCategory = foodCategoryRepository.findByName(foodDto.getFoodCategory());
        String foodPlusCatName = foodDto.getFoodCategory() + "-" + foodDto.getName();

        Food food = new Food(foodPlusCatName, foodDto.getServingSize(), foodDto.getCalories(), foodDto.getFat(), foodDto.getCarbs(),
                foodDto.getProtein(), foodCategory, new HashSet<Servings>());
        return foodRepository.save(food);
    }

    @Override
    public List<Food> loadFood() {
        return foodRepository.findAll();
    }
}
