package com.glenvasa.Fitness.App1.controller;

import com.glenvasa.Fitness.App1.dto.ExerciseDto;
import com.glenvasa.Fitness.App1.dto.FoodDto;
import com.glenvasa.Fitness.App1.model.Exercise;
import com.glenvasa.Fitness.App1.model.ExerciseCategory;
import com.glenvasa.Fitness.App1.model.Food;
import com.glenvasa.Fitness.App1.repository.ExerciseCategoryRepository;
import com.glenvasa.Fitness.App1.repository.ExerciseRepository;
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

@Controller
@RequestMapping("/food")
public class FoodController {


    private final FoodService foodService;

    private final FoodRepository foodRepository;
//    List<ExerciseCategory> exerciseCategoryList;



    @Autowired
    public FoodController(FoodService foodService, FoodRepository foodRepository) {
        this.foodService = foodService;
        this.foodRepository = foodRepository;
    }

    @GetMapping()
    public String displayFoodPage(Model model) {
        model.addAttribute("food", new FoodDto()); // binds "food" attribute to the model

        List<Food> foodList =  foodService.loadFood();
        model.addAttribute("foodBag", foodList);

        return "food";
    }

    @PostMapping
    public String saveFood(@ModelAttribute("food") FoodDto foodDto){
//        System.out.println("Ex Controller" + exerciseDto.getName() + exerciseDto.getDescription() + exerciseDto.getExerciseCategory());
//          System.out.println(exerciseDto.getExerciseCategory());

        foodService.save(foodDto);
        return "redirect:/food?success";
    }
}




