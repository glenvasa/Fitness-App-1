package com.glenvasa.Fitness.App1.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;

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

//    @ManyToOne
//    User user;    //Gives an eror b/c User_role table?????

    @ManyToOne
    MealType mealType;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL) // when we retrieve user, we retrieve all associated roles
    @JoinTable(
            name= "meal_food",
            joinColumns = @JoinColumn(
                    name = "meal_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name ="food_id", referencedColumnName = "id"
            )
    )
    private Collection<Food> food;

    public Meal(String date, User user, MealType mealType, Collection<Food> food) {
        this.date = date;
//        this.user = user;
        this.mealType = mealType;
        this.food = food;
    }
}
