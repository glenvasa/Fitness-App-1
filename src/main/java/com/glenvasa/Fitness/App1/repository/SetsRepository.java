package com.glenvasa.Fitness.App1.repository;

import com.glenvasa.Fitness.App1.model.Sets;
import com.glenvasa.Fitness.App1.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SetsRepository extends JpaRepository<Sets, Long> {

    @Query("from Sets s join Workout w on s.workout.id = w.id where w.user.id = ?1")
    List<Sets> findAllByUserId(Long id);

    @Override
    @Modifying
    @Transactional
    @Query("delete Sets s where s.id = ?1")
    void deleteById(Long aLong);


}