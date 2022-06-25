package com.glenvasa.Fitness.App1.repository;

import com.glenvasa.Fitness.App1.model.Meal;
import com.glenvasa.Fitness.App1.model.Sets;
import com.glenvasa.Fitness.App1.model.User;
import com.glenvasa.Fitness.App1.model.Workout;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class SetsRepositoryTests {

    @Autowired
    private SetsRepository setsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkoutRepository workoutRepository;

    @Test //passes // Sets are saved w/workout_id field // Workouts are saved with user_id field
    public void testFindAllSetsByUserId() {

        User user = new User();
        User savedUser = userRepository.save(user);

        Workout workout = new Workout();
        workout.setUser(savedUser);

        Workout savedWorkout = workoutRepository.save(workout);

        Sets sets1 = new Sets();
        Sets sets2 = new Sets();
        sets1.setWorkout(savedWorkout);
        sets2.setWorkout(savedWorkout);

        Sets savedSets1 = setsRepository.save(sets1);
        Sets savedSets2 = setsRepository.save(sets2);

        List<Sets> userSets = setsRepository.findAllByUserId(savedUser.getId());

        // Asserts that the List of Sets we retrieved that are connected to savedUser through the savedWorkout = 2
        Assertions.assertThat(userSets.size()).isEqualTo(2);

    }
}
