package com.glenvasa.Fitness.App1.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class FoodDto {
    private String name;
    private Float servingSize;
    private Integer calories;
    private Float fat;
    private Float carbs;
    private Float protein;


    public FoodDto(String name, Float servingSize, Integer calories, Float fat, Float carbs,
                   Float protein) {
        this.name = name;
        this.servingSize = servingSize;
        this.calories = calories;
        this.fat = fat;
        this.carbs = carbs;
        this.protein = protein;
    }

}