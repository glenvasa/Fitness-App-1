package com.glenvasa.Fitness.App1.controller;

import com.glenvasa.Fitness.App1.dto.ExerciseCategoryDto;
import com.glenvasa.Fitness.App1.dto.UserRegistrationDto;
import com.glenvasa.Fitness.App1.model.ExerciseCategory;
import com.glenvasa.Fitness.App1.service.ExerciseCategoryService;
import com.glenvasa.Fitness.App1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/exercise/category")
public class ExerciseCategoryController {

    private final ExerciseCategoryService categoryService;

    @Autowired
    public ExerciseCategoryController(ExerciseCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public String displayExerciseCategoryPage(Model model) {
        model.addAttribute("category", new ExerciseCategoryDto()); // binds "category" attribute to the model

        List<ExerciseCategory> exerciseCategoryList =  categoryService.loadExerciseCategories();
        model.addAttribute("categories", exerciseCategoryList);
        return "exercise_category";
    }

    @PostMapping
    public String saveCategory(@ModelAttribute("category") ExerciseCategoryDto categoryDto){
        categoryService.save(categoryDto);
        return "redirect:/exercise/category?success";
    }
}
