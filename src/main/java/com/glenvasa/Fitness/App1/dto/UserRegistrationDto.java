package com.glenvasa.Fitness.App1.dto;


import java.util.Date;

// Data that user enters in Registration Page UI Form
public class UserRegistrationDto {


        private String firstName;
        private String lastName;
        private String streetAddress;
        private String apartment;
        private String city;
        private String state;
        private Integer zipCode;
        private String phone1;
        private String phone2;
        private String email;
        private String password;
        private Float height;
        private Date dateOfBirth;


        public UserRegistrationDto() {
        }

        public UserRegistrationDto(String firstName, String lastName, String streetAddress, String apartment, String city,
                    String state, Integer zipCode, String phone1, String phone2, String email, String password,
                    Float height, Date dateOfBirth) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.streetAddress = streetAddress;
            this.apartment = apartment;
            this.city = city;
            this.state = state;
            this.zipCode = zipCode;
            this.phone1 = phone1;
            this.phone2 = phone2;
            this.email = email;
            this.password = password;
            this.height = height;
            this.dateOfBirth = dateOfBirth;

        }

        public Integer getZipCode() {
            return zipCode;
        }

        public void setZipCode(Integer zipCode) {
            this.zipCode = zipCode;
        }

        public String getPhone1() {
            return phone1;
        }

        public void setPhone1(String phone1) {
            this.phone1 = phone1;
        }

        public String getPhone2() {
            return phone2;
        }

        public void setPhone2(String phone2) {
            this.phone2 = phone2;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Float getHeight() {
            return height;
        }

        public void setHeight(Float height) {
            this.height = height;
        }

        public Date getDateOfBirth() {
            return dateOfBirth;
        }

        public void setDateOfBirth(Date dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }


        public String getStreetAddress() {
            return streetAddress;
        }

        public void setStreetAddress(String streetAddress) {
            this.streetAddress = streetAddress;
        }

        public String getApartment() {
            return apartment;
        }

        public void setApartment(String apartment) {
            this.apartment = apartment;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }


        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }

