package com.glenvasa.Fitness.App1.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class WorkoutDto {
    private String workoutName;
    private String dateOfWorkout;
    private Float duration;

    public WorkoutDto(String workoutName, String dateOfWorkout, Float duration){
        this.workoutName = workoutName;
        this.dateOfWorkout = dateOfWorkout;
        this.duration = duration;
    }
}
