package com.glenvasa.Fitness.App1.controller;

import com.glenvasa.Fitness.App1.dto.SmsRequestDto;
import com.glenvasa.Fitness.App1.model.*;
import com.glenvasa.Fitness.App1.repository.HealthProfileRepository;
import com.glenvasa.Fitness.App1.repository.MealRepository;
import com.glenvasa.Fitness.App1.repository.WorkoutRepository;
import com.glenvasa.Fitness.App1.service.SetsService;
import com.glenvasa.Fitness.App1.service.UserService;
import com.glenvasa.Fitness.App1.textUser.SmsRequest;
import com.glenvasa.Fitness.App1.utilClass.PRStats;
import com.glenvasa.Fitness.App1.utilClass.SelectedDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
  private final SetsService setsService;

  List<Meal> dailyMeals;
  List<Workout> dailyWorkouts;
  Double dailyCals = 0.0;
  Double modalDailyCals = 0.0;
  Double modalDailyProtein = 0.0;
  Double modalDailyCarbs = 0.0;
  Double modalDailyFat = 0.0;


  LocalDate mealDate;
  LocalDate workoutDate;
  List<HealthProfile> healthProfiles;
  HealthProfile currentHealthProfile;
  HealthProfile dailyHealthProfile;
  Double dailyTargetCals = 0.0;
  Double dailyWeight = 0.0;
//  SelectedDate selectedDate;

    @Autowired
    public ProfileController(UserService userService, MealRepository mealRepository, WorkoutRepository workoutRepository,
                             HealthProfileRepository healthProfileRepository, SetsService setsService){
        this.userService = userService;
        this.mealRepository = mealRepository;
        this.workoutRepository = workoutRepository;
        this.healthProfileRepository = healthProfileRepository;
        this.setsService = setsService;
    }

    @GetMapping("/profile")
    public String displayUserProfile(Principal principal, Model model) {
        String email = principal.getName();
        User user = userService.loadUserByEmail(email);
        model.addAttribute("user", user);

        List<Meal> meals = mealRepository.findAllByUserId(user.getId());

//        meals.forEach(meal -> dailyCals += meal.getMealCals());

        Set<LocalDate> mealDates = meals.stream().map(meal -> meal.getDate()).collect(Collectors.toSet());

        //
        model.addAttribute("selectedDate", new SelectedDate());
        // for each date in mealDates retrieve list of Meals and use .size to display next to date as # of meals for the day

        model.addAttribute("mealDates", mealDates);
        model.addAttribute("dailyCals", dailyCals);

        List<Workout> workouts = workoutRepository.findAllByUserId(user.getId());
        Set<LocalDate> workoutDates = workouts.stream().map(Workout::getDateOfWorkout).collect(Collectors.toSet());
//        System.out.println(workoutDates);
        model.addAttribute("workoutDates", workoutDates);

        model.addAttribute("dailyMeals", dailyMeals);
        model.addAttribute("dailyWorkouts", dailyWorkouts);

        model.addAttribute("modalDailyCals", modalDailyCals);
        model.addAttribute("modalDailyProtein", Math.round(modalDailyProtein));
        model.addAttribute("modalDailyCarbs", Math.round(modalDailyCarbs));
        model.addAttribute("modalDailyFat", Math.round(modalDailyFat));





        model.addAttribute("mealDate", mealDate);
        model.addAttribute("workoutDate", workoutDate);

        // Get all user's healthProfiles, get most recent, add to model to display
        healthProfiles = healthProfileRepository.findAll().stream().filter(healthProfile1 -> healthProfile1.getUser().getId() == user.getId()).collect(Collectors.toList());

        if(healthProfiles.size() > 0){
            currentHealthProfile = healthProfiles.get(healthProfiles.size() - 1);

        } else {
            currentHealthProfile = new HealthProfile(LocalDate.now(), 0.00F, (double) 0, (double) 0, "", user);
        }
        model.addAttribute("healthProfile", currentHealthProfile);

      model.addAttribute("targetCals", currentHealthProfile.getTargetCalories());
        model.addAttribute("dailyTargetCals", dailyTargetCals);
        model.addAttribute("dailyWeight", dailyWeight);
        System.out.println("Inside GET /profile");
        System.out.println(currentHealthProfile);

//        model.addAttribute("smsRequestDto", new SmsRequestDto());
        //        System.out.println(dailyMeals);
//        System.out.println(modalDailyCals);

        //Code to get User's Personal Records
//        List<Sets> allSets = setsService.loadSetsByUserId(principal);
//
//        // ExerciseStats is util class containing 3 fields (weight / repetitions / dateOfWorkout)
//        Map<String, PRStats> personalRecords = new HashMap<>();
//
//        allSets.forEach(sets -> {
//            String exerciseName = sets.getExercise().getName();
//            Float weight = sets.getWeight();
//            Integer repetitions = sets.getRepetitions();
//            LocalDate dateOfWorkout = sets.getWorkout().getDateOfWorkout();
//            PRStats prStats = new PRStats(weight, repetitions, dateOfWorkout);
//
//            // if key/exerciseName doesn't exist in Map, create it value ExerciseStats(weight / repetitions)
//            personalRecords.putIfAbsent(exerciseName, prStats);
//
//            // if key exists, compare value(exerciseStats).getWeight() with current set weight and replace value with current exerciseStats if less
//            personalRecords.computeIfPresent(exerciseName, (key, value) -> value.getWeight() >= weight ? value : prStats);
//        });
//
////       personalRecords.forEach((key,value) -> System.out.println(key + value.getWeight()));
//
//        model.addAttribute("personalRecords", personalRecords);


        return "profile";
    }


    @PostMapping("/profile/meals")
    public String displayMealsSummary(@ModelAttribute("selectedDate") SelectedDate selectedDate, Principal principal, Model model) {
        String email = principal.getName();
        User user = userService.loadUserByEmail(email);
        List<Meal> meals = mealRepository.findAllByUserId(user.getId());
        mealDate = LocalDate.parse(selectedDate.getDate());
        dailyMeals = meals.stream().filter(meal -> Objects.equals(meal.getDate(), mealDate)).toList();
//        model.addAttribute("dailyMeals", dailyMeals);
        modalDailyCals = 0.0;
        modalDailyProtein = 0.0;
        modalDailyCarbs = 0.0;
        modalDailyFat = 0.0;

        dailyMeals.forEach(meal -> {
            modalDailyCals += meal.getMealCals();
            modalDailyProtein += meal.getMealProtein();
            modalDailyCarbs += meal.getMealCarbs();
            modalDailyFat += meal.getMealFat();
        });


        List<Workout> workouts = workoutRepository.findAllByUserId(user.getId());
//        workoutDate = LocalDate.parse(selectedDate.getDate());
        dailyWorkouts = workouts.stream().filter(workout -> Objects.equals(workout.getDateOfWorkout(), mealDate)).toList();

        List<HealthProfile> dailyHealthProfiles = healthProfileRepository.findDailyByUserId(user.getId(), mealDate);
//       List<HealthProfile> allHealthProfiles = healthProfileRepository.findAll().stream().filter(healthProfile1 -> healthProfile1.getUser().getId() == user.getId() && healthProfile1.getDate() == mealDate).collect(Collectors.toList());
//       List<HealthProfile> dailyHealthProfiles = allHealthProfiles.stream().filter(healthProfile2 -> healthProfile2.getDate() == mealDate).collect(Collectors.toList());
       System.out.println(mealDate);
       System.out.println(dailyHealthProfiles);
//       System.out.println(dailyHealthProfiles);
       //sets curentHealthProfile to the last of the list of hps created for a date (use most recent as user may create many for a day but last is one they stick with)
       // the only issue I need to address is if user didn't create an hp for that date, I should find most recent hp created before the date
        if(dailyHealthProfiles.size() > 0){
            dailyHealthProfile = dailyHealthProfiles.get(dailyHealthProfiles.size() - 1);
        } else {
            dailyHealthProfile = currentHealthProfile;
        }


        if(dailyHealthProfile.getTargetCalories() == null){
            dailyTargetCals = 0.0;
        } else {
            dailyTargetCals = dailyHealthProfile.getTargetCalories();
        }
        dailyWeight = Double.valueOf(dailyHealthProfile.getWeight());
        System.out.println("Your daily weight is" + dailyHealthProfile.getWeight());
        model.addAttribute("dailyTargetCals", dailyTargetCals);
        model.addAttribute("dailyWeight", dailyWeight);

        System.out.println("hello from Profile Controller GET /profile/meals/");
//        System.out.println(currentHealthProfile); //accurately printing most recent hp but not updating
//
//
//        System.out.println("dailyMeals" + dailyMeals);
        System.out.println("dailyHealthProfile" + dailyHealthProfile);
        System.out.println("dailyTargetCals" + dailyHealthProfile.getTargetCalories());
        System.out.println("modalDailyCals" + modalDailyCals);

        return "redirect:/profile"; // may not need "redirect:/profile"
    }






}
