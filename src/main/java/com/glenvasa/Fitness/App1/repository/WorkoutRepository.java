package com.glenvasa.Fitness.App1.repository;

import com.glenvasa.Fitness.App1.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {

    Workout findTopByOrderByIdDesc();

    @Modifying
    @Query("update Workout w set w.dateOfWorkout = ?1, w.duration = ?2, w.workoutName = ?3 where w.id = ?4")
    void updateWorkoutById(String dateOfWorkout, Float duration, String workoutName, Long id);
}