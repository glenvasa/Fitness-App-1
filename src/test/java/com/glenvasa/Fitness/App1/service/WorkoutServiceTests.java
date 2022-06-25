package com.glenvasa.Fitness.App1.service;

import com.glenvasa.Fitness.App1.model.Meal;
import com.glenvasa.Fitness.App1.model.Workout;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.security.Principal;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class WorkoutServiceTests {


    @Autowired
    private WorkoutServiceImpl workoutService;

    @Test // passes
    public void testSaveWorkout() {

        Principal principal = new Principal() {
            @Override
            public String getName() {
                return "Bill";
            }
        };


        Workout savedWorkout = workoutService.save(principal);
        Assertions.assertThat(savedWorkout.getId()).isNotNull();
    }

}


