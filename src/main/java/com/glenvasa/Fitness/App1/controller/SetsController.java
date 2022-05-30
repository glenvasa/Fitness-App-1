package com.glenvasa.Fitness.App1.controller;


import com.glenvasa.Fitness.App1.dto.ExerciseDto;
import com.glenvasa.Fitness.App1.dto.SetsDto;
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
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/sets")
public class SetsController {

    @Autowired
    private final SetsService setsService;
    private final SetsRepository setsRepository;
    private final ExerciseRepository exerciseRepository;
    private final WorkoutRepository workoutRepository;

    List<Exercise> exerciseList;
    List<Sets> currentWorkoutSets;
    List<Sets> setsList;


    @Autowired
    public SetsController(SetsService exerciseService, SetsRepository setsRepository,
                          ExerciseRepository exerciseRepository, WorkoutRepository workoutRepository) {
        this.setsService = exerciseService;
        this.setsRepository = setsRepository;
        this.exerciseRepository = exerciseRepository;
        this.workoutRepository = workoutRepository;
    }

    @GetMapping()
    public String displaySets(Model model) {
        model.addAttribute("set", new SetsDto()); // binds "sets" attribute to the model

        // want to only display Sets that have just been created and linked to newly created Workout
        setsList =  setsService.loadSets();
//        Workout currentWorkout = workoutRepository.findTopByOrderByIdDesc();
//        List<Sets> currentWorkoutSets = setsList.stream().filter(s -> s.getWorkout().getId() == currentWorkout.getId()).collect(Collectors.toList());

        model.addAttribute("sets", setsList);

        exerciseList = exerciseRepository.findAll();
        model.addAttribute("exercises", exerciseList);

        return "sets";
    }

    @PostMapping
    public String saveSet(@ModelAttribute("sets") SetsDto setsDto){
//        System.out.println("Ex Controller" + exerciseDto.getName() + exerciseDto.getDescription() + exerciseDto.getExerciseCategory());
//          System.out.println(exerciseDto.getExerciseCategory());
        setsService.save(setsDto);
        // want to only display Sets that have just been created and linked to newly created Workout

        Workout currentWorkout = workoutRepository.findTopByOrderByIdDesc();
//        List<Sets> setsList =  setsService.loadSets();
//        currentWorkoutSets = setsList.stream().filter(s -> s.getWorkout().getId() == currentWorkout.getId()).collect(Collectors.toList());
       setsList.forEach(s -> System.out.println(s));
        return "redirect:/sets?success";
    }
}
