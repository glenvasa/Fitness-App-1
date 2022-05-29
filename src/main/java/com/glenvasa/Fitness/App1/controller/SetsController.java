package com.glenvasa.Fitness.App1.controller;


import com.glenvasa.Fitness.App1.dto.ExerciseDto;
import com.glenvasa.Fitness.App1.dto.SetsDto;
import com.glenvasa.Fitness.App1.model.Exercise;
import com.glenvasa.Fitness.App1.model.ExerciseCategory;
import com.glenvasa.Fitness.App1.model.Sets;
import com.glenvasa.Fitness.App1.repository.ExerciseCategoryRepository;
import com.glenvasa.Fitness.App1.repository.ExerciseRepository;
import com.glenvasa.Fitness.App1.repository.SetsRepository;
import com.glenvasa.Fitness.App1.service.ExerciseService;
import com.glenvasa.Fitness.App1.service.SetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/sets")
public class SetsController {

    @Autowired
    private final SetsService setsService;

    private final SetsRepository setsRepository;
    private final ExerciseRepository exerciseRepository;
    List<Exercise> exerciseList;



    @Autowired
    public SetsController(SetsService exerciseService, SetsRepository setsRepository, ExerciseRepository exerciseRepository) {
        this.setsService = exerciseService;
        this.setsRepository = setsRepository;
        this.exerciseRepository = exerciseRepository;
    }

    @GetMapping()
    public String displayExercisePage(Model model) {
        model.addAttribute("set", new SetsDto()); // binds "sets" attribute to the model

        List<Sets> setsList =  setsService.loadSets();
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
        return "redirect:/sets?success";
    }
}
