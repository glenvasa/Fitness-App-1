package com.glenvasa.Fitness.App1.service;

import com.glenvasa.Fitness.App1.dto.MealDto;
import com.glenvasa.Fitness.App1.dto.WorkoutDto;
import com.glenvasa.Fitness.App1.model.Meal;
import com.glenvasa.Fitness.App1.model.User;
import com.glenvasa.Fitness.App1.model.Workout;
import com.glenvasa.Fitness.App1.repository.MealRepository;
import com.glenvasa.Fitness.App1.repository.SetsRepository;
import com.glenvasa.Fitness.App1.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class MealServiceImpl implements MealService {


    private final MealRepository mealRepository;
    private final UserService userService;

    @Autowired
    public MealServiceImpl(MealRepository mealRepository, UserService userService) {
        this.mealRepository = mealRepository;
        this.userService = userService;
    }

    // to create workout before adding sets, other data; just user object and auto created id
    @Override
    public Meal save(Principal principal) {
        String email = principal.getName();
        User user = userService.loadUserByEmail(email);
        Meal meal = new Meal(user);
        return mealRepository.save(meal);
    }

    // don't believe using this method; will likely delete
    @Override
    public Meal save(MealDto mealDto, Principal principal) {
        return null;
    }

    // save workout after adding name,duration, date info AFTER creating/adding all Sets
    @Override
    public void update(MealDto mealDto, Meal meal) {
        mealRepository.updateMealById(mealDto.getDate(), mealDto.getTime(), mealDto.getMealType(), meal.getId());
    }



    @Override
    public List<Meal> loadMeals() {
        return mealRepository.findAll();
    }
}

