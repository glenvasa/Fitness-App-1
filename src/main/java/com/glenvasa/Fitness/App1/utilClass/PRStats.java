package com.glenvasa.Fitness.App1.utilClass;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PRStats {

    private Float weight;
    private Integer repetitions;
    private LocalDate dateOfWorkout;

    public PRStats(Float weight, Integer repetitions, LocalDate dateOfWorkout){
        this.weight = weight;
        this.repetitions = repetitions;
        this.dateOfWorkout = dateOfWorkout;
    }
}
