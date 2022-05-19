package com.glenvasa.Fitness.App1.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "sets")
@Getter
@Setter
@NoArgsConstructor
public class Sets {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private Integer repetitions;
        private Float weight;

        public Sets(Integer repetitions, Float weight){
                this.repetitions = repetitions;
                this.weight = weight;
        }
}
