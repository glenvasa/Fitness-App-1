package com.glenvasa.Fitness.App1.service;

import com.glenvasa.Fitness.App1.dto.ExerciseDto;
import com.glenvasa.Fitness.App1.dto.SetsDto;
import com.glenvasa.Fitness.App1.model.Exercise;
import com.glenvasa.Fitness.App1.model.ExerciseCategory;
import com.glenvasa.Fitness.App1.model.Sets;
import com.glenvasa.Fitness.App1.model.Workout;
import com.glenvasa.Fitness.App1.repository.ExerciseCategoryRepository;
import com.glenvasa.Fitness.App1.repository.ExerciseRepository;
import com.glenvasa.Fitness.App1.repository.SetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class SetsServiceImpl implements SetsService {


    private final SetsRepository setsRepository;
    private final ExerciseRepository exerciseRepository;

    @Autowired
    public SetsServiceImpl(SetsRepository setsRepository, ExerciseRepository exerciseRepository) {
        this.setsRepository = setsRepository;
        this.exerciseRepository = exerciseRepository;
    }


    @Override
    public Sets save(SetsDto setsDto) {
//        System.out.println("Ex Serv Impl" + exerciseDto.getName() + exerciseDto.getDescription() + exerciseDto.getExerciseCategory());
        Exercise exercise = exerciseRepository.findByName(setsDto.getExercise());
//        System.out.println(category.toString()); // this Correctly prints Category object
        Sets sets = new Sets(setsDto.getRepetitions(), setsDto.getWeight(), exercise, new Workout());
//        Exercise exercise = new Exercise("Bicep curls", "Workout with dumbbells", new ExerciseCategory("arm exercises", "biceps, triceps, etc."));
//        System.out.println(exerciseDto.getName() + exerciseDto.getDescription() + category); // THIs correctly prints the name, description and category
//        System.out.println(exercise.toString());// BUT this shows all fields in Exercise object as Null
        return setsRepository.save(sets);
    }



    @Override
    public List<Sets> loadSets() {
        return setsRepository.findAll();
    }
}

