package com.glenvasa.Fitness.App1.controller;

import com.glenvasa.Fitness.App1.model.Meal;
import com.glenvasa.Fitness.App1.model.User;
import com.glenvasa.Fitness.App1.model.Workout;
import com.glenvasa.Fitness.App1.repository.MealRepository;
import com.glenvasa.Fitness.App1.repository.WorkoutRepository;
import com.glenvasa.Fitness.App1.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class ProfileController {

  private final UserService userService;
  private final MealRepository mealRepository;
  private final WorkoutRepository workoutRepository;
  private User user;


    public ProfileController(UserService userService, MealRepository mealRepository, WorkoutRepository workoutRepository){
        this.userService = userService;
        this.mealRepository = mealRepository;
        this.workoutRepository = workoutRepository;
    }

    @GetMapping("/profile")
    public String displayUserProfile(Principal principal, Model model) {
        String email = principal.getName();
        user = userService.loadUserByEmail(email);
        model.addAttribute("user", user);

        List<Meal> meals = mealRepository.findAllByUserId(user.getId());
        List mealDates = meals.stream().map(meal -> meal.getDate()).collect(Collectors.toList());
        model.addAttribute("mealDates", mealDates);

        List<Workout> workouts = workoutRepository.findAllByUserId(user.getId());
        List<String> workoutDates = workouts.stream().map(Workout::getDateOfWorkout).collect(Collectors.toList());
//        System.out.println(workoutDates);
        model.addAttribute("workoutDates", workoutDates);

        return "profile";
    }

    @GetMapping("/profile/meals/{date}")
    public String displayMealsSummary(@PathVariable String date, Model model) {

        List<Meal> meals = mealRepository.findAllByUserId(user.getId());
        List<Meal> dailyMeals = meals.stream().filter(meal -> Objects.equals(meal.getDate(), date)).toList();
        model.addAttribute("dailyMeals", dailyMeals);

        System.out.println("hello from Profile Controller");
        System.out.println(date);
        System.out.println(dailyMeals.size());
        return "profile"; // may not need "redirect:/profile"
    }
}
