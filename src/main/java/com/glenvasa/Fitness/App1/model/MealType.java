//package com.glenvasa.Fitness.App1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//@Entity
//@Table(name = "mealType")
//@NoArgsConstructor
//@Getter
//@Setter
//@ToString
//public class MealType {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    String name;
//
//    @JsonIgnore
//    @OneToMany(mappedBy = "mealType")
//    private Set<Meal> meals = new HashSet<>();
//
//    public MealType(String name){
//        this.name = name;
//    }
//}
