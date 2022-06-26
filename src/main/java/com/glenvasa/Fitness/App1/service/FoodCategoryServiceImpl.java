package com.glenvasa.Fitness.App1.service;

import com.glenvasa.Fitness.App1.dto.FoodCategoryDto;
import com.glenvasa.Fitness.App1.model.Food;
import com.glenvasa.Fitness.App1.model.FoodCategory;
import com.glenvasa.Fitness.App1.repository.FoodCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

// Saves/Loads Food Categories
@Service
public class FoodCategoryServiceImpl implements FoodCategoryService{

    private final FoodCategoryRepository categoryRepository;

    @Autowired
    public FoodCategoryServiceImpl(FoodCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public FoodCategory save(FoodCategoryDto categoryDto) {
        FoodCategory foodCategory = new FoodCategory(categoryDto.getName(), categoryDto.getDescription(), new HashSet<Food>());
        return categoryRepository.save(foodCategory);
    }

    @Override
    public List<FoodCategory> loadFoodCategories() {
        return categoryRepository.findAll();
    }
}
