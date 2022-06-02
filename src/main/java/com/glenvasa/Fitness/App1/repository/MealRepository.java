package com.glenvasa.Fitness.App1.repository;

import com.glenvasa.Fitness.App1.model.Meal;
import com.glenvasa.Fitness.App1.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {

    Meal findTopByOrderByIdDesc();

    @Modifying
    @Transactional
    @Query("update Meal m set m.date = ?1, m.time = ?2, m.mealType = ?3 where m.id = ?4")
    void updateMealById(String date, String time, String mealType, Long id);


}
