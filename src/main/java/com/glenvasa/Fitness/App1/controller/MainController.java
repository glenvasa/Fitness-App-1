package com.glenvasa.Fitness.App1.controller;



import com.glenvasa.Fitness.App1.model.PersonalRecords;
import com.glenvasa.Fitness.App1.model.Sets;
import com.glenvasa.Fitness.App1.service.SetsService;
import com.glenvasa.Fitness.App1.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    public String home(Principal principal){
        List<Sets> allSets = setsService.loadSetsByUserId(principal);
//            System.out.println("The number of sets for " + principal.getName() + " is " + allSets.size());

    Map<String, Float> personalRecords = new HashMap<>();
    allSets.forEach(sets -> {
        String exerciseName = sets.getExercise().getName();
        Float weight = sets.getWeight();


        //retrieves current maxWeight value for exerciseName key
//        Float maxWeight = personalRecords.get(exerciseName); // may not need this code

        // if key/exerciseName doesn't exist in Map, create it with weight as value
        personalRecords.putIfAbsent(exerciseName, weight);
        // if key exists, compare value with weight and replace if weight > value
        personalRecords.computeIfPresent(exerciseName, (key,value) -> value >= weight ? value : weight);

//        if(weight > maxWeight){
//            // replace current maxWeight with weight if weight > maxWeight
//             personalRecords.put(exerciseName, weight);
//        }




//        Map<String, List<Item>> items = new HashMap<>();
//        items.computeIfAbsent(key, k -> new ArrayList<>()).add(item);

    });
        System.out.println(personalRecords);

         return "index";

}



}



