package com.glenvasa.Fitness.App1.repository;

import com.glenvasa.Fitness.App1.model.Meal;
import com.glenvasa.Fitness.App1.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {

    Meal findTopByOrderByIdDesc();

    @Modifying
    @Transactional
    @Query("update Meal m set m.date = ?1, m.time = ?2, m.mealType = ?3, m.mealCals = ?4 where m.id = ?5")
    void updateMealById(LocalDate date, LocalTime time, String mealType, Float mealCals, Long id);


    @Query("from Meal m where m.user.id = ?1")
    List<Meal> findAllByUserId(Long id);

    @Query("from Meal m where m.id = ?1")
    Meal findMealById(Long id);

    @Modifying
    @Transactional
    @Query("delete Meal m where m.id = ?1")
    void deleteMealById(Long id);


}
