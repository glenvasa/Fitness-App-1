package com.glenvasa.Fitness.App1.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PersonalRecords {

    private Float weight;
    private String name;

      PersonalRecords(Float weight, String name){
          this.weight = weight;
          this.name = name;
      }

}
