package com.glenvasa.Fitness.App1.controller;

import com.glenvasa.Fitness.App1.dto.ExerciseDto;
import com.glenvasa.Fitness.App1.dto.UserRegistrationDto;
import com.glenvasa.Fitness.App1.model.Workout;
import com.glenvasa.Fitness.App1.service.UserService;
import com.glenvasa.Fitness.App1.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class WorkoutController {

    private final WorkoutService workoutService;

    @Autowired
    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @GetMapping("/workouts")
    public String displayWorkoutsPage(Model model){
        List<Workout> workouts = workoutService.loadWorkouts();
        model.addAttribute("workouts", workouts); // binds "workouts" attribute to the model right before page loads

        return "workouts";
    }

    @PostMapping("/workout/create")
    public String createWorkout(Principal principal) {
        workoutService.save(principal);
        return "redirect:/sets";

    }

//    @PostMapping("/workout/create")
//    public String createWorkout(@ModelAttribute("workout") workoutDto workoutDto){
////        System.out.println("Ex Controller" + exerciseDto.getName() + exerciseDto.getDescription() + exerciseDto.getExerciseCategory());
////          System.out.println(exerciseDto.getExerciseCategory());
//
//        exerciseService.save(exerciseDto);
//        return "redirect:/exercise?success";
//


    // May be easier to save workouts from Set Service
//    @PostMapping("/workout/save")
//    public String saveWorkout(@ModelAttribute("") workoutDto workoutDto){
//        workoutService.save(workoutDto);
//        return "redirect:/workout?success";
//    }

}
