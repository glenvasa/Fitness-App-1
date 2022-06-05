package com.glenvasa.Fitness.App1.controller;

import com.glenvasa.Fitness.App1.model.Meal;
import com.glenvasa.Fitness.App1.model.User;
import com.glenvasa.Fitness.App1.model.Workout;
import com.glenvasa.Fitness.App1.repository.MealRepository;
import com.glenvasa.Fitness.App1.repository.WorkoutRepository;
import com.glenvasa.Fitness.App1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Controller
public class ProfileController {

  private final UserService userService;
  private final MealRepository mealRepository;
  private final WorkoutRepository workoutRepository;
  List<Meal> dailyMeals;
  Double dailyCals;

    @Autowired
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
        Set mealDates = meals.stream().map(meal -> meal.getDate()).collect(Collectors.toSet());
        model.addAttribute("mealDates", mealDates);

        List<Workout> workouts = workoutRepository.findAllByUserId(user.getId());
        List<String> workoutDates = workouts.stream().map(Workout::getDateOfWorkout).collect(Collectors.toList());
//        System.out.println(workoutDates);
        model.addAttribute("workoutDates", workoutDates);

        model.addAttribute("dailyMeals", dailyMeals);
        model.addAttribute("dailyCals", dailyCals);

        System.out.println("Inside GET /profile");
        System.out.println(dailyMeals);
        return "profile";
    }

    @GetMapping("/profile/meals/{date}")
    public String displayMealsSummary(@PathVariable String date, Principal principal, Model model) {
        String email = principal.getName();
        User user = userService.loadUserByEmail(email);
        List<Meal> meals = mealRepository.findAllByUserId(user.getId());
        dailyMeals = meals.stream().filter(meal -> Objects.equals(meal.getDate(), date)).toList();
//        model.addAttribute("dailyMeals", dailyMeals);
        dailyCals = 0.0;
        dailyMeals.forEach(meal -> dailyCals += meal.getMealCals());

        System.out.println("hello from Profile Controller GET /profile/meals/date");
        System.out.println(date);
        System.out.println("dailyMeals" + dailyMeals);
        return "redirect:/profile"; // may not need "redirect:/profile"
    }
}
