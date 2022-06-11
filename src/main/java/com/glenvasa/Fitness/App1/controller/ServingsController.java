package com.glenvasa.Fitness.App1.controller;

import com.glenvasa.Fitness.App1.dto.MealDto;
import com.glenvasa.Fitness.App1.dto.ServingsDto;
import com.glenvasa.Fitness.App1.dto.SetsDto;
import com.glenvasa.Fitness.App1.dto.WorkoutDto;
import com.glenvasa.Fitness.App1.model.*;
import com.glenvasa.Fitness.App1.repository.*;
import com.glenvasa.Fitness.App1.service.MealService;
import com.glenvasa.Fitness.App1.service.ServingsService;
import com.glenvasa.Fitness.App1.service.SetsService;
import com.glenvasa.Fitness.App1.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping
public class ServingsController {

        private final ServingsService servingsService;
        private final FoodRepository foodRepository;
        private final MealService mealService;
        private final MealRepository mealRepository;

        List<Food> foodList;
        //    List<Sets> currentWorkoutSets;
        List<Servings> servingsList;


        @Autowired
        public ServingsController(ServingsService servingsService, FoodRepository foodRepository,
                                  MealService mealService, MealRepository mealRepository) {
            this.servingsService = servingsService;
            this.foodRepository = foodRepository;
            this.mealService = mealService;
            this.mealRepository = mealRepository;
        }

        @GetMapping("/servings")
        public String displayServings(Model model) {
            model.addAttribute("serving", new ServingsDto()); // binds "serving" attribute to the model
            model.addAttribute("meal", new MealDto()); // binds "meal" object
            // want to only display Sets that have just been created and linked to newly created Workout
            servingsList =  servingsService.loadServings();
            Meal currentMeal = mealRepository.findTopByOrderByIdDesc();
//            System.out.println(currentMeal.getId());


                List<Servings> currentMealServings = servingsList.stream()
                        .filter(s -> s.getMeal().getId() == currentMeal.getId()).collect(Collectors.toList());

                model.addAttribute("servings", currentMealServings);


            foodList = foodRepository.findAll();
            model.addAttribute("food", foodList);

            return "servings";
        }

        @PostMapping("/servings")
        public String saveServing(@ModelAttribute("servings") ServingsDto servingsDto){
            servingsService.save(servingsDto);
            return "redirect:/servings?success";
        }

        @PostMapping("/servings/delete/{servingId}")
        public String deleteServing(@PathVariable Long servingId) {
        servingsService.deleteServing(servingId);
        return "redirect:/servings";
        }

        @PostMapping("/meal/update")
        public String saveMeal(@ModelAttribute("meal") MealDto mealDto){
            Meal currentMeal = mealRepository.findTopByOrderByIdDesc();
            mealService.update(mealDto, currentMeal);
            return "redirect:/meals";
        }
    }

