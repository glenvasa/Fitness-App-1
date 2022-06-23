package com.glenvasa.Fitness.App1;

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

//    private Long userId;
//    private String userEmail;

    @Test
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

//        userEmail = savedUser.getEmail();
//        userId = savedUser.getId();

        User existUser = entityManager.find(User.class, savedUser.getId());

        assertThat(existUser.getEmail()).isEqualTo(user.getEmail());

    }

//    @Test
//    public void UpdateUserById() {
//
//        User user = new User();
//        user.setFirstName("John");
//        user.setLastName("Smith");
//        user.setStreetAddress("12345 Main Street");
//        user.setCity("Boston");
//        user.setState("MA");
//        user.setZipCode("02121");
//        user.setPhone("16175551212");
//        user.setEmail("jsmith1@test.com");
//        user.setPassword("12345");
//        user.setHeight(72F);
//        user.setDateOfBirth("1982-12-16");
//        user.setRoles(Arrays.asList(new Role("ROLE_USER")));
//        user.setWorkout(new HashSet<Workout>());
//        user.setMeal(new HashSet<Meal>());
//        user.setHealthProfiles(new HashSet<HealthProfile>());
//
//        User savedUser = userRepository.save(user);
//        String newFirstName = "James";
//
//        userRepository.updateUserById(newFirstName, savedUser.getLastName(), savedUser.getStreetAddress(), savedUser.getCity(),
//                savedUser.getState(), savedUser.getZipCode(), savedUser.getPhone(), savedUser.getHeight(), savedUser.getDateOfBirth(), savedUser.getId());
//
////        User updatedUser = entityManager.find(User.class, savedUser.getId());
//        User updatedUser = userRepository.findByEmail(savedUser.getEmail());
//        assertThat(newFirstName).isEqualTo(updatedUser.getFirstName());
//
//    }

    @Test
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
}