package com.glenvasa.Fitness.App1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "exerciseCategory")
public class ExerciseCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "exerciseCategory")
    private Set<Exercise> exercises = new HashSet<>();

    public ExerciseCategory(String name, String description, Set<Exercise> exercises) {
        this.name = name;
        this.description = description;
        this.exercises = exercises;
    }


}
