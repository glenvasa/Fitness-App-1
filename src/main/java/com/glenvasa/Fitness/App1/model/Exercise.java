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




}