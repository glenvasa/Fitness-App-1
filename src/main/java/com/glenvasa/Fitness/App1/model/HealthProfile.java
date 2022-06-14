package com.glenvasa.Fitness.App1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "healthProfile")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class HealthProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "weight")
    private Float weight;

    @Column(name = "exercise_level")
    private Integer exerciseLevel;

    @Column(name = "maintenance_calories")
    private Double maintenanceCalories;

   // changed to mirror Workout and Meal ManyToOne properties.
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;

    public HealthProfile(LocalDate date, Float weight, Integer exerciseLevel, Double maintenanceCalories, User user){
        this.date = date;
        this.weight = weight;
        this.exerciseLevel = exerciseLevel;
        this.maintenanceCalories = maintenanceCalories;
        this.user = user;
    }
}

