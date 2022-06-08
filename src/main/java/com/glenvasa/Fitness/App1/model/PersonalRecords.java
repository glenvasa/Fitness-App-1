package com.glenvasa.Fitness.App1.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PersonalRecords {

//    String dateOfWorkout;
    Float weight;
    String name;

      PersonalRecords(Float weight, String name){
          this.weight = weight;
          this.name = name;
      }

}
