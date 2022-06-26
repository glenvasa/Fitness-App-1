package com.glenvasa.Fitness.App1.controller;

import com.glenvasa.Fitness.App1.dto.ExerciseDto;
import com.glenvasa.Fitness.App1.dto.FoodDto;
import com.glenvasa.Fitness.App1.model.Exercise;
import com.glenvasa.Fitness.App1.model.ExerciseCategory;
import com.glenvasa.Fitness.App1.model.Food;
import com.glenvasa.Fitness.App1.model.FoodCategory;
import com.glenvasa.Fitness.App1.repository.ExerciseCategoryRepository;
import com.glenvasa.Fitness.App1.repository.ExerciseRepository;
import com.glenvasa.Fitness.App1.repository.FoodCategoryRepository;
import com.glenvasa.Fitness.App1.repository.FoodRepository;
import com.glenvasa.Fitness.App1.service.ExerciseService;
import com.glenvasa.Fitness.App1.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

// Class contains routes/logic to display all Food objects stored in DB and to create/save a new Food object.
@Controller
@RequestMapping("/food")
public class FoodController {

    private final FoodService foodService;
    private final FoodRepository foodRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    List<FoodCategory> foodCategoryList;


    @Autowired
    public FoodController(FoodService foodService, FoodRepository foodRepository, FoodCategoryRepository foodCategoryRepository) {
        this.foodService = foodService;
        this.foodRepository = foodRepository;
        this.foodCategoryRepository = foodCategoryRepository;
    }

    @GetMapping
    public String displayFoodPage(Model model) {
        model.addAttribute("food", new FoodDto()); // binds "food" attribute to the model

        List<Food> foodList =  foodService.loadFood();
        model.addAttribute("foodBag", foodList);

        foodCategoryList = foodCategoryRepository.findAll();
        model.addAttribute("categories", foodCategoryList);

        return "food";
    }

    @PostMapping
    public String saveFood(@ModelAttribute("food") FoodDto foodDto){
        try {
            foodService.save(foodDto);
        } catch (Exception e){
            System.out.println("The following error occurred when attempting to save a Food to the Database: " + e.getMessage());
        }
        return "redirect:/food?success";
    }
}




