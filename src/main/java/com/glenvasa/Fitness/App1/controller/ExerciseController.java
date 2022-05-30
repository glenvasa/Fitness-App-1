package com.glenvasa.Fitness.App1.controller;


import com.glenvasa.Fitness.App1.dto.ExerciseDto;
import com.glenvasa.Fitness.App1.model.Exercise;
import com.glenvasa.Fitness.App1.model.ExerciseCategory;
import com.glenvasa.Fitness.App1.repository.ExerciseCategoryRepository;
import com.glenvasa.Fitness.App1.repository.ExerciseRepository;
import com.glenvasa.Fitness.App1.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/exercise")
public class ExerciseController {

    @Autowired
    private final ExerciseService exerciseService;

    private final ExerciseCategoryRepository exerciseCategoryRepository;
    private final ExerciseRepository exerciseRepository;
    List<ExerciseCategory> exerciseCategoryList;



    @Autowired
    public ExerciseController(ExerciseService exerciseService, ExerciseCategoryRepository exerciseCategoryRepository, ExerciseRepository exerciseRepository) {
        this.exerciseService = exerciseService;
        this.exerciseCategoryRepository = exerciseCategoryRepository;
        this.exerciseRepository = exerciseRepository;
    }

    @GetMapping()
    public String displayExercisePage(Model model) {
        model.addAttribute("exercise", new ExerciseDto()); // binds "exercise" attribute to the model

        List<Exercise> exerciseList =  exerciseService.loadExercises();
        model.addAttribute("exercises", exerciseList);

        exerciseCategoryList = exerciseCategoryRepository.findAll();
        model.addAttribute("categories", exerciseCategoryList);

        return "exercise";
    }

    @PostMapping
    public String saveExercise(@ModelAttribute("exercise") ExerciseDto exerciseDto){
//        System.out.println("Ex Controller" + exerciseDto.getName() + exerciseDto.getDescription() + exerciseDto.getExerciseCategory());
//          System.out.println(exerciseDto.getExerciseCategory());

        exerciseService.save(exerciseDto);
        return "redirect:/exercise?success";
    }
}
