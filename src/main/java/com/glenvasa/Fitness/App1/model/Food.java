package com.glenvasa.Fitness.App1.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "food")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String name;
    Float servingSize;
    Integer calories;
    Float fat;
    Float carbs;
    Float protein;

    public Food(String name, Float servingSize, Integer calories, Float fat, Float carbs, Float protein) {
        this.name = name;
        this.servingSize = servingSize;
        this.calories = calories;
        this.fat = fat;
        this.carbs = carbs;
        this.protein = protein;
    }
}
