package com.glenvasa.Fitness.App1.service;

import com.glenvasa.Fitness.App1.dto.FoodCategoryDto;
import com.glenvasa.Fitness.App1.model.FoodCategory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class FoodCategoryServiceTests {

    @Autowired
    private FoodCategoryServiceImpl foodCategoryService;


    @Test
    public void testSaveFoodCategory(){
        FoodCategoryDto foodCategoryDto = new FoodCategoryDto();
        FoodCategory savedFoodCategory = foodCategoryService.save(foodCategoryDto);

        Assertions.assertThat(savedFoodCategory.getId()).isNotNull();
    }

}
