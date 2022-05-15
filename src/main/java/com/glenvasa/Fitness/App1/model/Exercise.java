package com.glenvasa.Fitness.App1.model;

import javax.persistence.*;

@Entity
@Table(name = "exercise")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "exerciseCategory_id")
    private ExerciseCategory exerciseCategory;

    public Exercise() {
    }

    public Exercise(Long id, String name, String description, ExerciseCategory exerciseCategory) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.exerciseCategory = exerciseCategory;
    }

    public Exercise(String name, String description, ExerciseCategory exerciseCategory) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public ExerciseCategory getExerciseCategory() {
        return exerciseCategory;
    }

    public void setExerciseCategory(ExerciseCategory exerciseCategory) {
        this.exerciseCategory = exerciseCategory;
    }


}