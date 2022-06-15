package com.glenvasa.Fitness.App1.repository;


import com.glenvasa.Fitness.App1.model.Exercise;
import com.glenvasa.Fitness.App1.model.HealthProfile;
import com.glenvasa.Fitness.App1.model.User;
import com.glenvasa.Fitness.App1.model.Workout;
import com.glenvasa.Fitness.App1.service.HealthProfileService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Repository
public interface HealthProfileRepository extends JpaRepository<HealthProfile, Long> {

    HealthProfile findTopByOrderByIdDesc();

//    @Modifying
//    @Transactional
//    @Query("insert Meal m where m.id = ?1")
//    HealthProfile save(LocalDate date, Float weight, Integer exerciseLevel, Double maintenanceCalories, User user);
}
