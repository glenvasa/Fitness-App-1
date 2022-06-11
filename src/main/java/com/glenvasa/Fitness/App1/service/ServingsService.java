package com.glenvasa.Fitness.App1.service;

import com.glenvasa.Fitness.App1.dto.ServingsDto;
import com.glenvasa.Fitness.App1.model.Servings;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServingsService {
    Servings save(ServingsDto servingsDto);
    List<Servings> loadServings();

    void deleteServing(Long id);
}
