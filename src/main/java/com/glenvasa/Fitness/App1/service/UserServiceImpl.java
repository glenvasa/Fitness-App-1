package com.glenvasa.Fitness.App1.service;

import com.glenvasa.Fitness.App1.dto.UserRegistrationDto;
import com.glenvasa.Fitness.App1.model.*;
import com.glenvasa.Fitness.App1.repository.ServingsRepository;
import com.glenvasa.Fitness.App1.repository.SetsRepository;
import com.glenvasa.Fitness.App1.repository.UserRepository;
import com.glenvasa.Fitness.App1.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final WorkoutRepository workoutRepository;
    private final SetsRepository setsRepository;
    private final ServingsRepository servingsRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, WorkoutRepository workoutRepository,
                           SetsRepository setsRepository, ServingsRepository servingsRepository) {
        this.userRepository = userRepository;
        this.workoutRepository = workoutRepository;
        this.setsRepository = setsRepository;
        this.servingsRepository = servingsRepository;
    }

    @Override
    public User save(UserRegistrationDto registrationDto) {

        User user = new User(registrationDto.getFirstName(), registrationDto.getLastName(),
                registrationDto.getStreetAddress(), registrationDto.getCity(),
                registrationDto.getState(), registrationDto.getZipCode(), registrationDto.getPhone1(),
                registrationDto.getEmail(), new BCryptPasswordEncoder().encode(registrationDto.getPassword()),
                registrationDto.getHeight(), registrationDto.getWeight(), registrationDto.getDateOfBirth(),
                Arrays.asList(new Role("ROLE_USER")), new HashSet<Workout>(), new HashSet<Meal>());
        return userRepository.save(user);
    }

    @Override
    public User loadUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void update(UserRegistrationDto userRegistrationDto, Principal principal) {
        String email = principal.getName();
        Long userId = userRepository.findByEmail(email).getId();

        userRepository.updateUserById(userRegistrationDto.getFirstName(), userRegistrationDto.getLastName(), userRegistrationDto.getStreetAddress(),
                userRegistrationDto.getCity(), userRegistrationDto.getState(), userRegistrationDto.getZipCode(), userRegistrationDto.getPhone1(),
                userRegistrationDto.getHeight(), userRegistrationDto.getWeight(), userRegistrationDto.getDateOfBirth(), userId);

    }

    @Override
    public void updateUserMaintCals(Integer maintCals, Principal principal) {
        String email = principal.getName();
        Long userId = userRepository.findByEmail(email).getId();
        userRepository.updateUserMaintCals(maintCals, userId);
    }


    @Override
    public void delete(Principal principal) {
        String email = principal.getName();
        User user = userRepository.findByEmail(email);
        Set<Workout> workouts = user.getWorkout();
        Set<Meal> meals = user.getMeal();
        workouts.forEach(workout -> workout.getSets().forEach(sets -> setsRepository.deleteById(sets.getId())));
        meals.forEach(meal -> meal.getServings().forEach(serving -> servingsRepository.deleteById(serving.getId())));
        //        workouts.forEach(workout -> workoutRepository.deleteById(workout.getId()));
        userRepository.deleteById(user.getId());
    }


    @Override
    // abstract method in UserDetailsService that must be overridden
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);

        if(user == null){
            throw new UsernameNotFoundException("Invalid username");
        }

        return new org.springframework.security.core.userdetails
                .User(user.getEmail(), user.getPassword(), mapRolesToAuthorities((user.getRoles())));
    }

    // converts Collection of Roles into Collection of GrantedAuthorities
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){

        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());


    }
}
