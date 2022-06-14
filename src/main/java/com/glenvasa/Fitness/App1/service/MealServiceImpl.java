package com.glenvasa.Fitness.App1.service;

import com.glenvasa.Fitness.App1.dto.MealDto;
import com.glenvasa.Fitness.App1.dto.WorkoutDto;
import com.glenvasa.Fitness.App1.model.Meal;
import com.glenvasa.Fitness.App1.model.User;
import com.glenvasa.Fitness.App1.model.Workout;
import com.glenvasa.Fitness.App1.repository.MealRepository;
import com.glenvasa.Fitness.App1.repository.ServingsRepository;
import com.glenvasa.Fitness.App1.repository.SetsRepository;
import com.glenvasa.Fitness.App1.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class MealServiceImpl implements MealService {


    private final MealRepository mealRepository;
    private final UserService userService;
    private final ServingsRepository servingsRepository;

    @Autowired
    public MealServiceImpl(MealRepository mealRepository, UserService userService, ServingsRepository servingsRepository) {
        this.mealRepository = mealRepository;
        this.userService = userService;
        this.servingsRepository = servingsRepository;
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
        int formattedHour;
        int hour = mealDto.getHour();
        String dayNight = mealDto.getDayNight();

        if((dayNight.toLowerCase().startsWith("a") && hour != 12) || dayNight.toLowerCase().startsWith("p") && hour == 12 ) {
            formattedHour = hour;
        } else if(dayNight.toLowerCase().startsWith("p") && hour != 12) {
            formattedHour = hour + 12;
        } else {
            formattedHour = 0;
        }


//        if(dayNight.toLowerCase().startsWith("p") && hour == 12){
//            formattedHour = hour;
//        }

        LocalDate date = LocalDate.of(mealDto.getYear(), mealDto.getMonth(), mealDto.getDay());
        LocalTime time = LocalTime.of(formattedHour, mealDto.getMinute(), 0);

        mealRepository.updateMealById(date, time, mealDto.getMealType(),
                mealDto.getMealCals() ,meal.getId());
    }



    @Override
    public List<Meal> loadMeals() {
        return mealRepository.findAll();
    }

    @Override
    public void deleteMeal(Long mealId) {
        Meal meal = mealRepository.findMealById(mealId);
        meal.getServings().forEach(servings -> servingsRepository.deleteById(servings.getId()));
        mealRepository.deleteMealById(mealId);
    }


}

