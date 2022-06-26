package com.glenvasa.Fitness.App1.utilClass;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

// Used in Profile page when user selects caledar date and Meals/Workouts for that date are retrieved/displayed
@Getter
@Setter
@ToString
@NoArgsConstructor
public class SelectedDate {

    private String date;

    public SelectedDate(String date) {
        this.date = date;
    }

}