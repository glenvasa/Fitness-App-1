package com.glenvasa.Fitness.App1.dto;


import com.glenvasa.Fitness.App1.model.ExerciseCategory;

public class ExerciseCategoryDto {

    private String name;
    private String description;
    private ExerciseCategory exerciseCategory;

    public ExerciseCategoryDto() {
    }

    public ExerciseCategoryDto(String name, String description, ExerciseCategory exerciseCategory) {
        this.name = name;
        this.description = description;
        this.exerciseCategory = exerciseCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ExerciseCategory getExerciseCategory() {
        return exerciseCategory;
    }

    public void setExerciseCategory(ExerciseCategory exerciseCategory) {
        this.exerciseCategory = exerciseCategory;
    }

}
