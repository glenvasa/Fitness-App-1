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
    private Integer exerciseLevel;
    private Double maintenanceCalories;

    public HealthProfileDto(Float weight, Integer exerciseLevel, Double maintenanceCalories) {
        this.weight = weight;
        this.exerciseLevel = exerciseLevel;
        this.maintenanceCalories = maintenanceCalories;
    }

}
