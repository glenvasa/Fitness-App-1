package com.glenvasa.Fitness.App1.service;

import com.glenvasa.Fitness.App1.model.*;
import com.glenvasa.Fitness.App1.repository.SetsRepository;
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
public class SetsServiceTests {


    @Autowired
    private SetsServiceImpl setsService;

    @Autowired
    private SetsRepository setsRepository;

    @Test
    public void testLoadSets() {
        Exercise exercise = new Exercise();
        Workout workout = new Workout();
        Sets sets = new Sets(10, 150F, exercise, workout);
        Sets savedSets = setsRepository.save(sets);

        List<Sets> setsList = setsService.loadSets();
        Assertions.assertThat(setsList.size()).isNotZero();
    }

}
