package com.glenvasa.Fitness.App1.controller;

import com.glenvasa.Fitness.App1.model.HealthProfile;
import com.glenvasa.Fitness.App1.model.Meal;
import com.glenvasa.Fitness.App1.model.User;
import com.glenvasa.Fitness.App1.model.Workout;
import com.glenvasa.Fitness.App1.repository.HealthProfileRepository;
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
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@Controller
public class ProfileController {

  private final UserService userService;
  private final MealRepository mealRepository;
  private final WorkoutRepository workoutRepository;
  private final HealthProfileRepository healthProfileRepository;

  List<Meal> dailyMeals;
  List<Workout> dailyWorkouts;
//  Double dailyCals = 0.0;
  Double modalDailyCals = 0.0;
  LocalDate mealDate;
  LocalDate workoutDate;
  HealthProfile currentHealthProfile;

    @Autowired
    public ProfileController(UserService userService, MealRepository mealRepository, WorkoutRepository workoutRepository, HealthProfileRepository healthProfileRepository){
        this.userService = userService;
        this.mealRepository = mealRepository;
        this.workoutRepository = workoutRepository;
        this.healthProfileRepository = healthProfileRepository;
    }

    @GetMapping("/profile")
    public String displayUserProfile(Principal principal, Model model) {
        String email = principal.getName();
        User user = userService.loadUserByEmail(email);
        model.addAttribute("user", user);

        List<Meal> meals = mealRepository.findAllByUserId(user.getId());

//        meals.forEach(meal -> dailyCals += meal.getMealCals());

        Set<LocalDate> mealDates = meals.stream().map(meal -> meal.getDate()).collect(Collectors.toSet());
        // for each date in mealDates retrieve list of Meals and use .size to display next to date as # of meals for the day

        model.addAttribute("mealDates", mealDates);
//        model.addAttribute("dailyCals", dailyCals);

        List<Workout> workouts = workoutRepository.findAllByUserId(user.getId());
        Set<LocalDate> workoutDates = workouts.stream().map(Workout::getDateOfWorkout).collect(Collectors.toSet());
//        System.out.println(workoutDates);
        model.addAttribute("workoutDates", workoutDates);

        model.addAttribute("dailyMeals", dailyMeals);
        model.addAttribute("dailyWorkouts", dailyWorkouts);
        model.addAttribute("modalDailyCals", modalDailyCals);
//        model.addAttribute("maintCals", user.getMaintCals());
        model.addAttribute("mealDate", mealDate);
        model.addAttribute("workoutDate", workoutDate);

        // Get all user's healthProfiles, get most recent, add to model to display
        List<HealthProfile> healthProfiles = healthProfileRepository.findAll().stream().filter(healthProfile1 -> healthProfile1.getUser().getId() == user.getId()).collect(Collectors.toList());

        if(healthProfiles.size() > 0){
            currentHealthProfile = healthProfiles.get(healthProfiles.size() - 1);

        } else {
            currentHealthProfile = new HealthProfile(LocalDate.now(), 0.00F, 0, (double) 0, user);
        }
        model.addAttribute("healthProfile", currentHealthProfile);
        //        findTopByOrderByIdDesc();


        System.out.println("Inside GET /profile");
        System.out.println(currentHealthProfile);

        //        System.out.println(dailyMeals);
//        System.out.println(modalDailyCals);
        return "profile";
    }


    @GetMapping("/profile/meals/{date}")
    public String displayMealsSummary(@PathVariable String date, Principal principal, Model model) {
        String email = principal.getName();
        User user = userService.loadUserByEmail(email);
        List<Meal> meals = mealRepository.findAllByUserId(user.getId());
        mealDate = LocalDate.parse(date);
        dailyMeals = meals.stream().filter(meal -> Objects.equals(meal.getDate(), mealDate)).toList();
//        model.addAttribute("dailyMeals", dailyMeals);
        modalDailyCals = 0.0;
        dailyMeals.forEach(meal -> modalDailyCals += meal.getMealCals());

        System.out.println("hello from Profile Controller GET /profile/meals/date");
        System.out.println(date);


        System.out.println("dailyMeals" + dailyMeals);
        return "redirect:/profile"; // may not need "redirect:/profile"
    }


    @GetMapping("/profile/workouts/{date}")
    public String displayWorkoutsSummary(@PathVariable String date, Principal principal, Model model) {
        String email = principal.getName();
        User user = userService.loadUserByEmail(email);
        List<Workout> workouts = workoutRepository.findAllByUserId(user.getId());
        workoutDate = LocalDate.parse(date);
        dailyWorkouts = workouts.stream().filter(workout -> Objects.equals(workout.getDateOfWorkout(), workoutDate)).toList();

        System.out.println("hello from Profile Controller GET /profile/workouts/{date}");
        System.out.println(date);


        System.out.println("dailyWorkouts" + dailyWorkouts);
        return "redirect:/profile"; // may not need "redirect:/profile"
    }





}
