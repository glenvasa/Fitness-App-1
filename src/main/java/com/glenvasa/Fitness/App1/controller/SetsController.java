package com.glenvasa.Fitness.App1.controller;


import com.glenvasa.Fitness.App1.dto.ExerciseDto;
import com.glenvasa.Fitness.App1.dto.SetsDto;
import com.glenvasa.Fitness.App1.dto.SmsRequestDto;
import com.glenvasa.Fitness.App1.dto.WorkoutDto;
import com.glenvasa.Fitness.App1.model.*;
import com.glenvasa.Fitness.App1.repository.ExerciseCategoryRepository;
import com.glenvasa.Fitness.App1.repository.ExerciseRepository;
import com.glenvasa.Fitness.App1.repository.SetsRepository;
import com.glenvasa.Fitness.App1.repository.WorkoutRepository;
import com.glenvasa.Fitness.App1.service.ExerciseService;
import com.glenvasa.Fitness.App1.service.SetsService;
import com.glenvasa.Fitness.App1.service.UserService;
import com.glenvasa.Fitness.App1.service.WorkoutService;
import com.glenvasa.Fitness.App1.textUser.SmsController;
import com.glenvasa.Fitness.App1.utilClass.PRStats;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping
public class SetsController {

    private final SetsService setsService;
    //    private final SetsRepository setsRepository;
    private final ExerciseRepository exerciseRepository;
    private final WorkoutService workoutService;
    private final WorkoutRepository workoutRepository;
    private final SmsController smsController;
    private final UserService userService;


    List<Exercise> exerciseList;
    //    List<Sets> currentWorkoutSets;
    List<Sets> setsList;


    @Autowired
    public SetsController(SetsService exerciseService, ExerciseRepository exerciseRepository, WorkoutRepository workoutRepository,
                          WorkoutService workoutService, SmsController smsController, UserService userService) {
        this.setsService = exerciseService;
//        this.setsRepository = setsRepository;
        this.exerciseRepository = exerciseRepository;
        this.workoutRepository = workoutRepository;
        this.workoutService = workoutService;
        this.smsController = smsController;
        this.userService = userService;
    }

    @GetMapping("/sets")
    public String displaySets(Model model) {
        model.addAttribute("set", new SetsDto()); // binds "sets" attribute to the model
        model.addAttribute("workout", new WorkoutDto()); // binds "workout" object
        // want to only display Sets that have just been created and linked to newly created Workout
        setsList = setsService.loadSets();
        Workout currentWorkout = workoutRepository.findTopByOrderByIdDesc();
        System.out.println(currentWorkout.getId());
        List<Sets> currentWorkoutSets = setsList.stream().filter(s -> s.getWorkout().getId() == currentWorkout.getId()).collect(Collectors.toList());

        model.addAttribute("sets", currentWorkoutSets);

        exerciseList = exerciseRepository.findAll();
        model.addAttribute("exercises", exerciseList);

        return "sets";
    }

    @PostMapping("/sets")
    public String saveSet(@ModelAttribute("sets") SetsDto setsDto) {
        setsService.save(setsDto);
        return "redirect:/sets?success";
    }

    @PostMapping("/sets/delete/{setId}")
    public String deleteSet(@PathVariable Long setId) {
        setsService.deleteSet(setId);
        return "redirect:/sets";
    }

    @PostMapping("/workout/update")
    public String saveWorkout(@ModelAttribute("workout") WorkoutDto workoutDto, Principal principal) {
        String email = principal.getName();
        User user = userService.loadUserByEmail(email);


        Workout currentWorkout = workoutRepository.findTopByOrderByIdDesc();
        workoutService.update(workoutDto, currentWorkout);

        //After saves a workout creates PR Map
        List<Sets> allSets = setsService.loadSetsByUserId(principal);

        // ExerciseStats is util class containing 3 fields (weight / repetitions / dateOfWorkout)
        // This creates a map of current Personal Records
        Map<String, PRStats> personalRecords = new HashMap<>();

        allSets.forEach(sets -> {
            String exerciseName = sets.getExercise().getName();
            Float weight = sets.getWeight();
            Integer repetitions = sets.getRepetitions();
            LocalDate dateOfWorkout = sets.getWorkout().getDateOfWorkout();
            Long workoutId = sets.getWorkout().getId();
            PRStats prStats = new PRStats(weight, repetitions, dateOfWorkout, workoutId);

            // if key/exerciseName doesn't exist in Map, create it value ExerciseStats(weight / repetitions)
            personalRecords.putIfAbsent(exerciseName, prStats);

            // if key exists, compare value(exerciseStats).getWeight() with current set weight and replace value with current exerciseStats if less
            personalRecords.computeIfPresent(exerciseName, (key, value) -> value.getWeight() >= weight ? value : prStats);
        });


        // This creates a map of best weights for current workout
        Map<String, PRStats> workoutStats = new HashMap<>();
        currentWorkout.getSets().forEach(sets -> {
            String exerciseName = sets.getExercise().getName();
            Float weight = sets.getWeight();
            Integer repetitions = sets.getRepetitions();
            LocalDate dateOfWorkout = sets.getWorkout().getDateOfWorkout();
            Long workoutId = sets.getWorkout().getId();
            PRStats prStats = new PRStats(weight, repetitions, dateOfWorkout, workoutId);

            // if key/exerciseName doesn't exist in Map, create it value ExerciseStats(weight / repetitions)
            workoutStats.putIfAbsent(exerciseName, prStats);

            // if key exists, compare value(exerciseStats).getWeight() with current set weight and replace value with current exerciseStats if less
            workoutStats.computeIfPresent(exerciseName, (key, value) -> value.getWeight() > weight ? value : prStats);
        });

        System.out.println(workoutStats);
        System.out.println(personalRecords);
        // TODO: need to see if each key/exercise of workoutStats exists in personalRecords; if it does
        // check to see if value in workoutStats is > value in personalRecords. If it is, PR reached and
        // make call to smsController.sendSms which sends text to user informing PR reached.

        workoutStats.forEach((k,v) -> {                                  // can likely replace >= with > and remove && id check
            if(personalRecords.containsKey(k) && (workoutStats.get(k).getWeight() >= personalRecords.get(k).getWeight()) && (currentWorkout.getId() == personalRecords.get(k).getWorkoutId())){
                String message = "Hey, " + user.getFirstName() +"! You just reached a new Personal Record for " + k + " with a weight of " + workoutStats.get(k).getWeight() + " lbs for " + workoutStats.get(k).getRepetitions() +  " repetitions. Keep up the great work!!!";
                String phoneNumber = user.getPhone();
//                String message = "You just hit a new Personal Record!";
                SmsRequestDto messageUser = new SmsRequestDto(phoneNumber, message);
                smsController.sendSms(messageUser);
            }

        });




        return "redirect:/workouts";

    }
}