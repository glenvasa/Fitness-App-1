package com.glenvasa.Fitness.App1.repository;

import com.glenvasa.Fitness.App1.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    Food findByName(String s);
}