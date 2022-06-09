package com.glenvasa.Fitness.App1.controller;

import com.glenvasa.Fitness.App1.dto.UserRegistrationDto;
import com.glenvasa.Fitness.App1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserRegistrationController {

    private final UserService userService;

    @Autowired
    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String displayRegistrationPage(Model model){
        model.addAttribute("user", new UserRegistrationDto()); // binds "user" attribute to the model right before page loads
        return "registration"; // returns registration.html
    }

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute("user") UserRegistrationDto userRegistrationDto){
        userService.save(userRegistrationDto);
         return "redirect:/registration?success";
    }

    @PostMapping("/profile/update")
    public String updateUser(@ModelAttribute("user") UserRegistrationDto userRegistrationDto){

        return "redirect:/profile";
    }

}
