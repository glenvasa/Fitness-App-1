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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

// Class contains routes/logic to display all of a User's Meal Cards, create an empty Meal upon clicking "Create a Meal" button on Homepage,
// and to delete a Meal from Meals Page.
@Controller
public class MealController {

    private final MealService mealService;
    private final UserService userService;

    @Autowired
    public MealController(MealService mealService, UserService userService) {
        this.mealService = mealService;
        this.userService = userService;
    }

    // displays only meals for logged-in user
    @GetMapping("/meals")
    public String displayMealsPage(Model model, Principal principal){
        String email = principal.getName();
        User user = userService.loadUserByEmail(email);
        List<Meal> meals = mealService.loadMeals().stream().filter(m -> m.getUser().getId() == user.getId()).collect(Collectors.toList());
        model.addAttribute("meals", meals); // binds "meals" attribute right before page loads

   //     calculate total meal cals and make available through "mealCals" attribute

        return "meals";
    }

    @PostMapping("/meal/create")
    public String createMeal(Principal principal) {
        try {
            mealService.save(principal);
        } catch (Exception e){
            System.out.println("The following error occurred when attempting to create and save a Meal to the Database: " + e.getMessage());
        }

        return "redirect:/servings";
    }

    @PostMapping("/meal/delete/{mealId}")
    public String deleteMeal(@PathVariable Long mealId){
        try {
            mealService.deleteMeal(mealId);
        } catch (Exception e){
            System.out.println("The following error occurred when attempting to delete a Meal from the Database: " + e.getMessage());
        }

        return "redirect:/meals";
    }

}