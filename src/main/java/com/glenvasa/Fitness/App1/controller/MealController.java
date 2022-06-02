package com.glenvasa.Fitness.App1.controller;

import com.glenvasa.Fitness.App1.model.Meal;
import com.glenvasa.Fitness.App1.model.User;
import com.glenvasa.Fitness.App1.model.Workout;
import com.glenvasa.Fitness.App1.repository.MealRepository;
import com.glenvasa.Fitness.App1.repository.WorkoutRepository;
import com.glenvasa.Fitness.App1.service.MealService;
import com.glenvasa.Fitness.App1.service.UserService;
import com.glenvasa.Fitness.App1.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MealController {

    private final MealService mealService;
    private final MealRepository mealRepository;
    private final UserService userService;

    @Autowired
    public MealController(MealService mealService, MealRepository mealRepository, UserService userService) {
        this.mealService = mealService;
        this.mealRepository = mealRepository;
        this.userService = userService;
    }

    @GetMapping("/meals") // should display only meals for logged in user
    public String displayMealsPage(Model model, Principal principal){
        String email = principal.getName();
        User user = userService.loadUserByEmail(email);
        List<Meal> meals = mealService.loadMeals().stream().filter(m -> m.getUser().getId() == user.getId()).collect(Collectors.toList());

        model.addAttribute("meals", meals); // binds "meals" attribute right before page loads

        return "meals";
    }

    @PostMapping("/meal/create")
    public String createMeal(Principal principal) {
        mealService.save(principal);
        return "redirect:/servings";

    }


}