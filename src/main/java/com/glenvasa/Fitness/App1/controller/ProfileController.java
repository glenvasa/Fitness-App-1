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

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class ProfileController {

  private final UserService userService;
  private final MealRepository mealRepository;
  private final WorkoutRepository workoutRepository;

    public ProfileController(UserService userService, MealRepository mealRepository, WorkoutRepository workoutRepository){
        this.userService = userService;
        this.mealRepository = mealRepository;
        this.workoutRepository = workoutRepository;
    }

    @GetMapping("/profile")
    public String displayUserProfile(Principal principal, Model model) {
        String email = principal.getName();
        User user = userService.loadUserByEmail(email);
        model.addAttribute("user", user);

        List<Meal> meals = mealRepository.findAllByUserId(user.getId());
        List mealDates = meals.stream().map(meal -> meal.getDate()).collect(Collectors.toList());
        model.addAttribute("mealDates", mealDates);

        List<Workout> workouts = workoutRepository.findAllByUserId(user.getId());
        List workoutDates = workouts.stream().map(workout -> workout.getDateOfWorkout()).collect(Collectors.toList());
        System.out.println(workoutDates);
        model.addAttribute("workoutDates", workoutDates);

        return "profile";
    }


}
