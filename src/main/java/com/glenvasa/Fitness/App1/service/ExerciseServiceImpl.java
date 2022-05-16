package com.glenvasa.Fitness.App1.service;

import com.glenvasa.Fitness.App1.dto.ExerciseDto;
import com.glenvasa.Fitness.App1.model.Exercise;
import com.glenvasa.Fitness.App1.model.ExerciseCategory;
import com.glenvasa.Fitness.App1.repository.ExerciseCategoryRepository;
import com.glenvasa.Fitness.App1.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseServiceImpl implements ExerciseService {


    private final ExerciseRepository exerciseRepository;
    private final ExerciseCategoryRepository exerciseCategoryRepository;

    @Autowired
    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, ExerciseCategoryRepository exerciseCategoryRepository) {
        this.exerciseRepository = exerciseRepository;
        this.exerciseCategoryRepository = exerciseCategoryRepository;
    }


    @Override
    public Exercise save(ExerciseDto exerciseDto) {
//        System.out.println("Ex Serv Impl" + exerciseDto.getName() + exerciseDto.getDescription() + exerciseDto.getExerciseCategory());
          ExerciseCategory category = exerciseCategoryRepository.findByName(exerciseDto.getExerciseCategory());
         System.out.println(category.toString()); // this Correctly prints Category object
          Exercise exercise = new Exercise(exerciseDto.getName(), exerciseDto.getDescription(), category);
//        Exercise exercise = new Exercise("Bicep curls", "Workout with dumbbells", new ExerciseCategory("arm exercises", "biceps, triceps, etc."));
        System.out.println(exerciseDto.getName() + exerciseDto.getDescription() + category); // THIs correctly prints the name, description and category
          System.out.println(exercise.toString());// BUT this shows all fields in Exercise object as Null
        return exerciseRepository.save(exercise);
    }

//    @Override
//    public Exercise save(ExerciseDto exerciseDto) {
//        return null;
//    }

    @Override
    public List<Exercise> loadExercises() {
        return exerciseRepository.findAll();
    }
}
