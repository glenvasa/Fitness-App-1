package com.glenvasa.Fitness.App1.dto;

import com.glenvasa.Fitness.App1.model.Exercise;
import com.glenvasa.Fitness.App1.model.ExerciseCategory;
import lombok.ToString;

@ToString
public class ExerciseDto {
    private String name;
    private String description;
    private String exerciseCategory;

    public ExerciseDto() {
    }

    public ExerciseDto(String name, String description, String exerciseCategory) {
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

    public String getExerciseCategory() {
        return exerciseCategory;
    }

    public void setExerciseCategory(String exerciseCategory) {
        this.exerciseCategory = exerciseCategory;
    }
}
