package com.glenvasa.Fitness.App1.repository;

import com.glenvasa.Fitness.App1.model.*;
import com.glenvasa.Fitness.App1.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

//    private User user1;
//    private Long userId;
//    private String userEmail;

//    private void setup(){
//        user1 = new User();
//        user1.setFirstName("John");
//        user1.setLastName("Smith");
//        user1.setStreetAddress("12345 Main Street");



    @Test //passes
    public void testCreateUser() {

        User user = new User();
        user.setFirstName("John");
        user.setLastName("Smith");
        user.setStreetAddress("12345 Main Street");
        user.setCity("Boston");
        user.setState("MA");
        user.setZipCode("02121");
        user.setPhone("16175551212");
        user.setEmail("jsmith@test.com");
        user.setPassword("12345");
        user.setHeight(72F);
        user.setDateOfBirth("1982-12-16");
        user.setRoles(Arrays.asList(new Role("ROLE_USER")));
        user.setWorkout(new HashSet<Workout>());
        user.setMeal(new HashSet<Meal>());
        user.setHealthProfiles(new HashSet<HealthProfile>());

        User savedUser = userRepository.save(user);



        User existUser = entityManager.find(User.class, savedUser.getId());

        assertThat(existUser.getEmail()).isEqualTo(user.getEmail());

    }



    @Test //passes
    public void findUserByEmailTest(){

        User user = new User();
        user.setFirstName("John");
        user.setLastName("Smith");
        user.setStreetAddress("12345 Main Street");
        user.setCity("Boston");
        user.setState("MA");
        user.setZipCode("02121");
        user.setPhone("16175551212");
        user.setEmail("jsmith1@test.com");
        user.setPassword("12345");
        user.setHeight(72F);
        user.setDateOfBirth("1982-12-16");
        user.setRoles(Arrays.asList(new Role("ROLE_USER")));
        user.setWorkout(new HashSet<Workout>());
        user.setMeal(new HashSet<Meal>());
        user.setHealthProfiles(new HashSet<HealthProfile>());

        User savedUser = userRepository.save(user);

        User retrievedUser = userRepository.findByEmail(savedUser.getEmail()); //userEmail was set to class field in testCreateUser Test

        Assertions.assertThat(retrievedUser.getEmail()).isEqualTo(savedUser.getEmail()); // this says the user Id for the retrieved user here is = to the userId set to class field in testCrateUser Test


    }

    @Test
    public void updateUserTest(){

        User user = new User();
        user.setFirstName("John");
        user.setLastName("Smith");
        user.setStreetAddress("12345 Main Street");
        user.setCity("Boston");
        user.setState("MA");
        user.setZipCode("02121");
        user.setPhone("16175551212");
        user.setEmail("jsmith2@test.com");
        user.setPassword("12345");
        user.setHeight(72F);
        user.setDateOfBirth("1982-12-16");
        user.setRoles(Arrays.asList(new Role("ROLE_USER")));
        user.setWorkout(new HashSet<Workout>());
        user.setMeal(new HashSet<Meal>());
        user.setHealthProfiles(new HashSet<HealthProfile>());

        User savedUser = userRepository.save(user);

//        savedUser.setFirstName("James");
//
//        User updatedUser = userRepository.save(savedUser);

        String newFirstName = "James";

       userRepository.updateUserById(newFirstName, savedUser.getLastName(), savedUser.getStreetAddress(), savedUser.getCity(),
                savedUser.getState(), savedUser.getZipCode(), savedUser.getPhone(), savedUser.getHeight(), savedUser.getDateOfBirth(), savedUser.getId());

        User retrievedUser = userRepository.findById(savedUser.getId()).get();

        Assertions.assertThat(retrievedUser.getFirstName()).isEqualTo("James");

//        expected: "James"
//        but was: "John",  why???? it is updated in DB as "James"
    }


    @Test
    public void deleteUserByIdTest(){

        User user1 = new User();
        user1.setFirstName("John");
        user1.setLastName("Smith");
        user1.setStreetAddress("12345 Main Street");
        user1.setCity("Boston");
        user1.setState("MA");
        user1.setZipCode("02121");
        user1.setPhone("16175551212");
        user1.setEmail("jsmith3@test.com");
        user1.setPassword("12345");
        user1.setHeight(72F);
        user1.setDateOfBirth("1982-12-16");
        user1.setRoles(Arrays.asList(new Role("ROLE_USER")));
        user1.setWorkout(new HashSet<Workout>());
        user1.setMeal(new HashSet<Meal>());
        user1.setHealthProfiles(new HashSet<HealthProfile>());

        User savedUser = userRepository.save(user1);
        User existUser = entityManager.find(User.class, savedUser.getId());

        userRepository.deleteById(existUser.getId());

        User retrievedUser = userRepository.findByEmail("jsmith3@test.com");

        Assertions.assertThat(retrievedUser).isNull();

    }
}