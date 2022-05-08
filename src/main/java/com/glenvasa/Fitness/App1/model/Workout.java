package com.glenvasa.Fitness.App1.model;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "workout")
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "workout_name")
    private String workoutName;

    @Column(name = "date_of_workout")
    private Date dateOfWorkout;

    private LocalTime duration;

    public Workout() {
    }

    public Workout(String workoutName, Date dateOfWorkout, LocalTime duration) {
        this.workoutName = workoutName;
        this.dateOfWorkout = dateOfWorkout;
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public Date getDateOfWorkout() {
        return dateOfWorkout;
    }

    public void setDateOfWorkout(Date dateOfWorkout) {
        this.dateOfWorkout = dateOfWorkout;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }
}
