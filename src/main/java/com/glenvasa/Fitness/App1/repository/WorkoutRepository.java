package com.glenvasa.Fitness.App1.repository;

import com.glenvasa.Fitness.App1.model.Meal;
import com.glenvasa.Fitness.App1.model.Workout;
import org.hibernate.jdbc.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {

    Workout findTopByOrderByIdDesc();

    @Modifying
    @Transactional
    @Query("update Workout w set w.dateOfWorkout = ?1, w.duration = ?2, w.workoutName = ?3 where w.id = ?4")
    void updateWorkoutById(String dateOfWorkout, Float duration, String workoutName, Long id);

    @Query("from Workout w where w.user.id = ?1")
    List<Workout> findAllByUserId(Long id);



//    Workout updateWorkoutById(Long id, Workout updatedWorkout);
}