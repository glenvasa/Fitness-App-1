package com.glenvasa.Fitness.App1;

import com.glenvasa.Fitness.App1.model.*;
import com.glenvasa.Fitness.App1.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

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
        User existUser = entityManager.find(User.class, savedUser.getId());

        assertThat(existUser.getEmail()).isEqualTo(user.getEmail());

    }

}
