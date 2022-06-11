package com.glenvasa.Fitness.App1.repository;

import com.glenvasa.Fitness.App1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email); // Spring data JPA creates a query based on method name

    @Modifying
    @Transactional
    @Query("update User u set u.firstName = ?1, u.lastName = ?2, u.streetAddress = ?3, u.city = ?4, u.state = ?5, " +
            "u.zipCode = ?6, u.phone1 = ?7, u.height = ?8, u.weight = ?9, u.dateOfBirth = ?10 where u.id = ?11")
    void updateUserById(String firstName, String lastName, String streetAddress, String city,
                           String state, String zipCode, String phone1, Float height, Float weight, String dateOfBirth, Long id);


    @Override
    @Modifying
    @Transactional
    @Query("delete User u where u.id = ?1")
    void deleteById(Long aLong);
}
