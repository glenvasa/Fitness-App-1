package com.glenvasa.Fitness.App1.controller;



import com.glenvasa.Fitness.App1.model.PersonalRecords;
import com.glenvasa.Fitness.App1.model.Sets;
import com.glenvasa.Fitness.App1.service.SetsService;
import com.glenvasa.Fitness.App1.service.WorkoutService;

import com.glenvasa.Fitness.App1.utilClass.PRStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.*;


@Controller
public class MainController {

    SetsService setsService;

    @Autowired
    MainController(SetsService setsService){
        this.setsService = setsService;
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
    public String home(Model model, Principal principal){
        List<Sets> allSets = setsService.loadSetsByUserId(principal);

        // ExerciseStats is util class containing 2 fields (weight / repetitions)
        Map<String, PRStats> personalRecords = new HashMap<>();

    allSets.forEach(sets -> {
        String exerciseName = sets.getExercise().getName();
        Float weight = sets.getWeight();
        Integer repetitions = sets.getRepetitions();
        String dateOfWorkout = sets.getWorkout().getDateOfWorkout();
        PRStats prStats = new PRStats(weight, repetitions, dateOfWorkout);

        // if key/exerciseName doesn't exist in Map, create it value ExerciseStats(weight / repetitions)
        personalRecords.putIfAbsent(exerciseName, prStats);

        // if key exists, compare value(exerciseStats).getWeight() with current set weight and replace value with current exerciseStats if less
        personalRecords.computeIfPresent(exerciseName, (key,value) -> value.getWeight() > weight ? value : prStats);
    });

//       personalRecords.forEach((key,value) -> System.out.println(key + value.getWeight()));

        model.addAttribute("personalRecords", personalRecords);

        return "index";

}



}



