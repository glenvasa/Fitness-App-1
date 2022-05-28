package com.glenvasa.Fitness.App1.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "sets")
@Getter
@Setter
@NoArgsConstructor
public class Sets {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private Integer repetitions;
        private Float weight;

        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name="workout_id", referencedColumnName = "id")
        private Workout workout;


        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name="exercise_id", referencedColumnName = "id")
        private Exercise exercise;


        public Sets(Integer repetitions, Float weight, Exercise exercise, Workout workout){
                this.repetitions = repetitions;
                this.weight = weight;
                this.exercise = exercise;
                this.workout = workout;
        }
}
