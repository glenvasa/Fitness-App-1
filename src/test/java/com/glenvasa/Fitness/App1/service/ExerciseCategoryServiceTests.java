package com.glenvasa.Fitness.App1.service;

import com.glenvasa.Fitness.App1.dto.ExerciseCategoryDto;
import com.glenvasa.Fitness.App1.model.ExerciseCategory;
import com.glenvasa.Fitness.App1.repository.ExerciseCategoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ExerciseCategoryServiceTests {

    @Autowired
    private ExerciseCategoryServiceImpl exerciseCategoryService;


    @Test // passes
    public void testSaveExerciseCategory(){
        ExerciseCategoryDto exerciseCategoryDto = new ExerciseCategoryDto();
        ExerciseCategory savedExerciseCategory = exerciseCategoryService.save(exerciseCategoryDto);

        Assertions.assertThat(savedExerciseCategory.getId()).isNotNull();
    }

}


