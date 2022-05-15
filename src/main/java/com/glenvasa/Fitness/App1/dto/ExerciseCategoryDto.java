package com.glenvasa.Fitness.App1.dto;



public class ExerciseCategoryDto {

    private String name;
    private String description;

    public ExerciseCategoryDto() {
    }

    public ExerciseCategoryDto(String name, String description) {
        this.name = name;
        this.description = description;
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
}
