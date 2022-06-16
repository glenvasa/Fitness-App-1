package com.glenvasa.Fitness.App1.utilClass;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

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