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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class WorkoutController {

    private final WorkoutService workoutService;
    private final WorkoutRepository workoutRepository;
    private final UserService userService;

    @Autowired
    public WorkoutController(WorkoutService workoutService, WorkoutRepository workoutRepository, UserService userService) {
        this.workoutService = workoutService;
        this.workoutRepository = workoutRepository;
        this.userService = userService;
    }

    @GetMapping("/workouts") // should display only workouts for logged in user
    public String displayWorkoutsPage(Model model, Principal principal){
        String email = principal.getName();
        User user = userService.loadUserByEmail(email);
        List<Workout> workouts = workoutService.loadWorkouts().stream().filter(wo -> wo.getUser().getId() == user.getId()).collect(Collectors.toList());
//        Workout workout = workoutRepository.findTopByOrderByIdDesc();
//        System.out.println(workout.toString());
//        List<Workout> filteredWorkouts = workouts.stream().filter(wo -> wo.getId() == workout.getId()).collect(Collectors.toList());
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
