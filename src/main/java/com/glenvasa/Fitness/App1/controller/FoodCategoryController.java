package com.glenvasa.Fitness.App1.controller;

import com.glenvasa.Fitness.App1.dto.ExerciseCategoryDto;
import com.glenvasa.Fitness.App1.dto.FoodCategoryDto;
import com.glenvasa.Fitness.App1.model.ExerciseCategory;
import com.glenvasa.Fitness.App1.model.FoodCategory;
import com.glenvasa.Fitness.App1.service.ExerciseCategoryService;
import com.glenvasa.Fitness.App1.service.FoodCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/food/category")
public class FoodCategoryController {

    private final FoodCategoryService categoryService;

    @Autowired
    public FoodCategoryController(FoodCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public String displayFoodCategoryPage(Model model) {
        model.addAttribute("category", new FoodCategoryDto()); // binds "category" attribute to the model

        List<FoodCategory> foodCategoryList =  categoryService.loadFoodCategories();
        model.addAttribute("categories", foodCategoryList);
        return "food_category";
    }

    @PostMapping
    public String saveCategory(@ModelAttribute("category") FoodCategoryDto categoryDto){
        categoryService.save(categoryDto);
        return "redirect:/food/category?success";
    }
}

