package com.glenvasa.Fitness.App1.controller;



import com.glenvasa.Fitness.App1.model.PersonalRecords;
import com.glenvasa.Fitness.App1.model.Sets;
import com.glenvasa.Fitness.App1.service.SetsService;
import com.glenvasa.Fitness.App1.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;


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
            System.out.println(allSets);
            return "index";
}



}



