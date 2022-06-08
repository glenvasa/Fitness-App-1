package com.glenvasa.Fitness.App1.repository;

import com.glenvasa.Fitness.App1.model.Meal;

import com.glenvasa.Fitness.App1.model.PersonalRecords;
import com.glenvasa.Fitness.App1.model.Workout;
import org.hibernate.jdbc.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

//    @Query("MAX(s.weight), e.name, from workout w, join sets s on w.id = s.workout.id, join exercise e on s.exercise.id = e.id, where w.user.id = ?1, group by e.name")
//    PersonalRecords findPersonalRecord(Long id);


//    @PersistenceContext
//    pEntityManager entityManager;
//
//    public List<Object[]> customQuery(int id) {
//        Query nativeQuery = entityManager.createNativeQuery(product_ordered).setParameter("id",id);
//        return nativeQuery.getResultList();
    }

