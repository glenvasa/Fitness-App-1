package com.glenvasa.Fitness.App1.service;

import com.glenvasa.Fitness.App1.dto.ExerciseDto;
import com.glenvasa.Fitness.App1.model.Exercise;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ExerciseServiceTests {


    @Autowired
    private ExerciseServiceImpl exerciseService;


    @Test
    public void testLoadExercises(){
        ExerciseDto exerciseDto = new ExerciseDto();
        Exercise exercise = exerciseService.save(exerciseDto); //saves a new Exercise to DB

        List<Exercise> exerciseList = exerciseService.loadExercises(); //loads all Exercises

        Assertions.assertThat(exerciseList.size()).isNotZero(); // we know there should be at least 1 Exercise in DB
    }

}

