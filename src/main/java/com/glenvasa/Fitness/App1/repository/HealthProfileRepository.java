package com.glenvasa.Fitness.App1.repository;

import com.glenvasa.Fitness.App1.model.HealthProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface HealthProfileRepository extends JpaRepository<HealthProfile, Long> {

    // finds HP object created most recently in DB overall
    HealthProfile findTopByOrderByIdDesc();

    @Query("from HealthProfile hp where hp.user.id = ?1 and hp.date =?2")
    List<HealthProfile> findDailyByUserId(Long id, LocalDate mealDate);


}
