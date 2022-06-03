package com.glenvasa.Fitness.App1.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class MealDto {
    private String date;
    private String time;
    private String mealType;
    private Float mealCals;

    public MealDto(String date, String time, String mealType, Float mealCals) {
        this.date = date;
        this.time = time;
        this.mealType = mealType;
        this.mealCals = mealCals;
    }
}