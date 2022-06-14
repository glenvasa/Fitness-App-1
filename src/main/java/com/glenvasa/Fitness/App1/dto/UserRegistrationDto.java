package com.glenvasa.Fitness.App1.dto;


import lombok.*;

import java.util.Date;

// Data that user enters in Registration Page UI Form
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserRegistrationDto {

        private String firstName;
        private String lastName;
        private String streetAddress;
        private String city;
        private String state;
        private String zipCode;
        private String phone;
        private String email;
        private String password;
        private Float height;
//        private Float weight;
        private String dateOfBirth;



    public UserRegistrationDto(String firstName, String lastName, String streetAddress, String city,
                               String state, String zipCode, String phone, String email, String password,
                               Float height, String dateOfBirth) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.streetAddress = streetAddress;
            this.city = city;
            this.state = state;
            this.zipCode = zipCode;
            this.phone = phone;
            this.email = email;
            this.password = password;
            this.height = height;
            this.dateOfBirth = dateOfBirth;
        }

        // used to update user information on Profile Page
        public UserRegistrationDto(String firstName, String lastName, String streetAddress, String city,
                                   String state, String zipCode, String phone, Float height, String dateOfBirth) {
                this.firstName = firstName;
                this.lastName = lastName;
                this.streetAddress = streetAddress;
                this.city = city;
                this.state = state;
                this.zipCode = zipCode;
                this.phone = phone;
                this.height = height;
                this.dateOfBirth = dateOfBirth;

        }


}

