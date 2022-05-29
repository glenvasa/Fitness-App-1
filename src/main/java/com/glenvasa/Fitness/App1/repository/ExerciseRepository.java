package com.glenvasa.Fitness.App1.repository;

import com.glenvasa.Fitness.App1.model.Exercise;
import com.glenvasa.Fitness.App1.model.ExerciseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    Exercise findByName(String s);
}
