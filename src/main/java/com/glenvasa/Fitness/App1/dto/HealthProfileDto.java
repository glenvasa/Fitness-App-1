package com.glenvasa.Fitness.App1.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
public class HealthProfileDto {

    private Float weight;
    private Double exerciseLevel;
    private Double targetCalories;
    private String weightGoal;

    public HealthProfileDto(Float weight, Double exerciseLevel, Double targetCalories, String weightGoal) {
        this.weight = weight;
        this.exerciseLevel = exerciseLevel;
        this.targetCalories = targetCalories;
        this.weightGoal = weightGoal;
    }

}
