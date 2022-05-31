package com.glenvasa.Fitness.App1.controller;


import com.glenvasa.Fitness.App1.dto.ExerciseDto;
import com.glenvasa.Fitness.App1.dto.SetsDto;
import com.glenvasa.Fitness.App1.dto.WorkoutDto;
import com.glenvasa.Fitness.App1.model.Exercise;
import com.glenvasa.Fitness.App1.model.ExerciseCategory;
import com.glenvasa.Fitness.App1.model.Sets;
import com.glenvasa.Fitness.App1.model.Workout;
import com.glenvasa.Fitness.App1.repository.ExerciseCategoryRepository;
import com.glenvasa.Fitness.App1.repository.ExerciseRepository;
import com.glenvasa.Fitness.App1.repository.SetsRepository;
import com.glenvasa.Fitness.App1.repository.WorkoutRepository;
import com.glenvasa.Fitness.App1.service.ExerciseService;
import com.glenvasa.Fitness.App1.service.SetsService;
import com.glenvasa.Fitness.App1.service.WorkoutService;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping
public class SetsController {

    @Autowired
    private final SetsService setsService;
    private final SetsRepository setsRepository;
    private final ExerciseRepository exerciseRepository;
    private final WorkoutService workoutService;
    private final WorkoutRepository workoutRepository;

    List<Exercise> exerciseList;
//    List<Sets> currentWorkoutSets;
    List<Sets> setsList;


    @Autowired
    public SetsController(SetsService exerciseService, SetsRepository setsRepository,
                          ExerciseRepository exerciseRepository, WorkoutRepository workoutRepository,
                          WorkoutService workoutService) {
        this.setsService = exerciseService;
        this.setsRepository = setsRepository;
        this.exerciseRepository = exerciseRepository;
        this.workoutRepository = workoutRepository;
        this.workoutService = workoutService;
    }

    @GetMapping("/sets")
    public String displaySets(Model model) {
        model.addAttribute("set", new SetsDto()); // binds "sets" attribute to the model
        model.addAttribute("workout", new WorkoutDto()); // binds "workout" object
        // want to only display Sets that have just been created and linked to newly created Workout
        setsList =  setsService.loadSets();
        Workout currentWorkout = workoutRepository.findTopByOrderByIdDesc();
        System.out.println(currentWorkout.getId());
        List<Sets> currentWorkoutSets = setsList.stream().filter(s -> s.getWorkout().getId() == currentWorkout.getId()).collect(Collectors.toList());

        model.addAttribute("sets", currentWorkoutSets);

        exerciseList = exerciseRepository.findAll();
        model.addAttribute("exercises", exerciseList);

        return "sets";
    }

    @PostMapping("/sets")
    public String saveSet(@ModelAttribute("sets") SetsDto setsDto){
        setsService.save(setsDto);
        return "redirect:/sets?success";
    }

    @PatchMapping("/workout/create")
    public String saveWorkout(@ModelAttribute("workout") WorkoutDto workoutDto){
        Workout workout = workoutRepository.findTopByOrderByIdDesc();
//        workoutService.update(workoutDto, workout);
        System.out.println(workoutDto.getDateOfWorkout() + workoutDto.getWorkoutName() + workoutDto.getDuration());
        return "workouts";
    }
}
