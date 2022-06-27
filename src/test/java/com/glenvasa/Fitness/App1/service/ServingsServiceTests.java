package com.glenvasa.Fitness.App1.service;


import com.glenvasa.Fitness.App1.model.Food;
import com.glenvasa.Fitness.App1.model.Meal;
import com.glenvasa.Fitness.App1.model.Servings;
import com.glenvasa.Fitness.App1.repository.ServingsRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.security.Principal;
import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ServingsServiceTests {


    @Autowired
    private ServingsServiceImpl servingsService;

    @Autowired
    private ServingsRepository servingsRepository;

    @Test
    public void testLoadServings() {


        Servings servings = new Servings(1F, new Meal(), new Food());
        Servings savedServings = servingsRepository.save(servings);

        List<Servings> servingsList = servingsService.loadServings();
        Assertions.assertThat(servingsList.size()).isNotZero();
    }

}


