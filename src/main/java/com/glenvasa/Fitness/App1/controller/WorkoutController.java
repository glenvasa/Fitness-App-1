package com.glenvasa.Fitness.App1.controller;

import com.glenvasa.Fitness.App1.dto.ExerciseDto;
import com.glenvasa.Fitness.App1.dto.UserRegistrationDto;
import com.glenvasa.Fitness.App1.model.User;
import com.glenvasa.Fitness.App1.model.Workout;
import com.glenvasa.Fitness.App1.repository.WorkoutRepository;
import com.glenvasa.Fitness.App1.service.UserService;
import com.glenvasa.Fitness.App1.service.WorkoutService;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

//Class contains logic to display all User's Workouts in DB, create an empty Workout
// by clicking "Create a Workout" button on Homepage which redirects to Sets Page, and to delete a Workout.
@Controller
public class WorkoutController {

    private final WorkoutService workoutService;
    private final UserService userService;

    @Autowired
    public WorkoutController(WorkoutService workoutService, UserService userService) {
        this.workoutService = workoutService;
        this.userService = userService;
    }

    // displays all Workouts in DB for logged-in user
    @GetMapping("/workouts")
    public String displayWorkoutsPage(Model model, Principal principal){
        String email = principal.getName();
        User user = userService.loadUserByEmail(email);
        List<Workout> workouts = workoutService.loadWorkouts().stream().filter(wo -> wo.getUser().getId() == user.getId()).collect(Collectors.toList());

        model.addAttribute("workouts", workouts); // binds "workouts" attribute to the model right before page loads

        return "workouts";
    }

    @PostMapping("/workout/create")
    public String createWorkout(Principal principal) {
        workoutService.save(principal);
        return "redirect:/sets";

    }

    @PostMapping("/workout/delete/{workoutId}")
    public String deleteWorkout(@PathVariable Long workoutId){
        workoutService.deleteWorkout(workoutId);
        return "redirect:/workouts";
    }


}
