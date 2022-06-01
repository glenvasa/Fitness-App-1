package com.glenvasa.Fitness.App1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "meal")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private String date;

    @Column(name = "time")
    private String time;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "mealType_id", referencedColumnName = "id")
//    private MealType mealType;
    private String mealType;

    @JsonIgnore
    @OneToMany(mappedBy = "meal")
    private Set<Servings> servings = new HashSet<>();

    public Meal(String date, String time, String mealType, User user, Set<Servings> servings) {
        this.date = date;
        this.time = time;
        this.user = user;
        this.mealType = mealType;
        this.servings = servings;
    }
}
