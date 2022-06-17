package com.glenvasa.Fitness.App1.controller;



import com.glenvasa.Fitness.App1.dto.HealthProfileDto;
import com.glenvasa.Fitness.App1.dto.UserRegistrationDto;
import com.glenvasa.Fitness.App1.model.HealthProfile;
import com.glenvasa.Fitness.App1.model.PersonalRecords;
import com.glenvasa.Fitness.App1.model.Sets;
import com.glenvasa.Fitness.App1.model.User;
import com.glenvasa.Fitness.App1.service.HealthProfileService;
import com.glenvasa.Fitness.App1.service.SetsService;
import com.glenvasa.Fitness.App1.service.UserService;
import com.glenvasa.Fitness.App1.service.WorkoutService;

import com.glenvasa.Fitness.App1.utilClass.PRStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;


@Controller
public class MainController {

    SetsService setsService;
    UserService userService;
    HealthProfileService healthProfileService;

    @Autowired
    MainController(SetsService setsService, UserService userService, HealthProfileService healthProfileService) {
        this.setsService = setsService;
        this.userService = userService;
        this.healthProfileService = healthProfileService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


//@GetMapping("/")
//    public String home(Principal principal){
//            PersonalRecords personalRecords = workoutService.getPersonalRecords(principal);
//            System.out.println(personalRecords);
//            return "index";
//}

    @GetMapping("/")
    public String home(Model model, Principal principal) {
        List<Sets> allSets = setsService.loadSetsByUserId(principal);

        // ExerciseStats is util class containing 3 fields (weight / repetitions / dateOfWorkout)
        Map<String, PRStats> personalRecords = new HashMap<>();

        allSets.forEach(sets -> {
            String exerciseName = sets.getExercise().getName();
            Float weight = sets.getWeight();
            Integer repetitions = sets.getRepetitions();
            LocalDate dateOfWorkout = sets.getWorkout().getDateOfWorkout();
            PRStats prStats = new PRStats(weight, repetitions, dateOfWorkout);

            // if key/exerciseName doesn't exist in Map, create it value ExerciseStats(weight / repetitions)
            personalRecords.putIfAbsent(exerciseName, prStats);

            // if key exists, compare value(exerciseStats).getWeight() with current set weight and replace value with current exerciseStats if less
            personalRecords.computeIfPresent(exerciseName, (key, value) -> value.getWeight() > weight ? value : prStats);
        });

//       personalRecords.forEach((key,value) -> System.out.println(key + value.getWeight()));

        model.addAttribute("personalRecords", personalRecords);

        //Retrieve User's Height and dateOfBirth from User and auto input to Maintenance Calories Form
        User user = userService.loadUserByEmail(principal.getName());
        Integer age = Period.between(LocalDate.parse(user.getDateOfBirth()), LocalDate.now()).getYears();
        System.out.println("You are " + age + " years old.");

        model.addAttribute("height", user.getHeight());
        model.addAttribute("age", age);
        model.addAttribute("levelOne", "You are sedentary and do not exercise.");
        model.addAttribute("levelTwo", "You exercise lightly 1-3 times per week.");
        model.addAttribute("levelThree", "You exercise 3 - 5 days per week.");
        model.addAttribute("levelFour", "You exercise 6 - 7 days per week.");
        model.addAttribute("levelFive", "You exercise 7 days a week and also have a physically demanding job.");

        model.addAttribute("healthProfile", new HealthProfileDto());

        LocalDate today = LocalDate.now();
        List<HealthProfile> todayHealthProfiles = healthProfileService.findTodayProfiles(principal, today);
        System.out.println(todayHealthProfiles.size() > 0);

//        model.addAttribute("dailyHp", "not-exists");
        if(todayHealthProfiles.size() > 0){
            model.addAttribute("dailyHp", "exists");
        }

        return "index";

    }

    @PostMapping("/user/healthProfile")
    public String createHealthProfile(@ModelAttribute("healthProfile") HealthProfileDto healthProfileDto, Principal principal){
        healthProfileService.save(healthProfileDto, principal);
        return "index";
    }

//    checks to see if any healthProfiles created by user for current date

//    @GetMapping("/user/healthProfile/today")
//    public String getDailyHealthProfile(Principal principal){
//        LocalDate today = LocalDate.now();
//        List<HealthProfile> todayHealthProfiles = healthProfileService.findTodayProfiles(principal, today);
//        System.out.println("Today's Health Profiles");
//         System.out.println(todayHealthProfiles);
//        return "redirect:/profile";
//    }


}


