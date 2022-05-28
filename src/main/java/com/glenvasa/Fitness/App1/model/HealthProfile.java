package com.glenvasa.Fitness.App1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
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

    @Column(name = "weight")
    private Float weight;

    @Column(name = "body_fat")
    private Float bodyFat;

    @Column(name = "steps")
    private Integer steps;

    @Column(name = "date")
    private String date;

    @Column(name = "time")
    private String time;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;

    public HealthProfile(String date, String time, Float weight, Float bodyFat, Integer steps, User user){
        this.date = date;
        this.time = time;
        this.weight = weight;
        this.bodyFat = bodyFat;
        this.steps = steps;
        this.user = user;
    }
}

