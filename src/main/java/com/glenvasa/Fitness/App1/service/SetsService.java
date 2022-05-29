package com.glenvasa.Fitness.App1.service;

import com.glenvasa.Fitness.App1.dto.ExerciseDto;
import com.glenvasa.Fitness.App1.dto.SetsDto;
import com.glenvasa.Fitness.App1.model.Exercise;
import com.glenvasa.Fitness.App1.model.Sets;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SetsService {
    Sets save(SetsDto setsDto);
    List<Sets> loadSets();
}
