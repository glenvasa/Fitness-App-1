package com.glenvasa.Fitness.App1.model;

import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "exerciseCategory")
@ToString
public class ExerciseCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

//    @OneToOne(cascade=CascadeType.ALL)
//    private Exercise exercise;

    public ExerciseCategory() {
    }

    public ExerciseCategory(String name, String description) {
        this.name = name;
        this.description = description;
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
}
