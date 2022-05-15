package com.glenvasa.Fitness.App1.repository;

import com.glenvasa.Fitness.App1.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
}
