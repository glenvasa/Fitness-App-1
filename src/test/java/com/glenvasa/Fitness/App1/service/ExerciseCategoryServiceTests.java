package com.glenvasa.Fitness.App1.service;

import com.glenvasa.Fitness.App1.dto.ExerciseCategoryDto;
import com.glenvasa.Fitness.App1.model.ExerciseCategory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;


@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ExerciseCategoryServiceTests {

    @Autowired
    private ExerciseCategoryServiceImpl exerciseCategoryService;


    @Test
    public void testSaveExerciseCategory(){
        ExerciseCategoryDto exerciseCategoryDto = new ExerciseCategoryDto();
        ExerciseCategory savedExerciseCategory = exerciseCategoryService.save(exerciseCategoryDto);

        Assertions.assertThat(savedExerciseCategory.getId()).isNotNull();
    }

}


