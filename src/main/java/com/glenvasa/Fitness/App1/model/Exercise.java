package com.glenvasa.Fitness.App1.model;

import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "exercise")
@ToString
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "description", length = 50)
    private String description;

    @ManyToOne(cascade=CascadeType.ALL)
//    @JoinColumn(name = "exerciseCategory_id")
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