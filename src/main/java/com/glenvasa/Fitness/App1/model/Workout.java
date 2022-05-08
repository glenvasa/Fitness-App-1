package com.glenvasa.Fitness.App1.model;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;
import java.util.Set;

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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name= "workout_exercise",
            joinColumns = @JoinColumn(
                    name = "workout_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name ="exercise_id", referencedColumnName = "id"
            )
    )
    private Set<Exercise> exercises;

    public Workout() {
    }


    public Workout(String workoutName, Date dateOfWorkout, LocalTime duration, User user, Set<Exercise> exercises) {
        this.workoutName = workoutName;
        this.dateOfWorkout = dateOfWorkout;
        this.duration = duration;
        this.user = user;
        this.exercises = exercises;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Set<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(Set<Exercise> exercises) {
        this.exercises = exercises;
    }

}
