package com.glenvasa.Fitness.App1.controller;

import com.glenvasa.Fitness.App1.model.ExerciseCategory;
import com.glenvasa.Fitness.App1.model.User;
import com.glenvasa.Fitness.App1.service.ExerciseCategoryService;
import com.glenvasa.Fitness.App1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
//
//    private final UserService userService;
//
//    @Autowired
//    public MainController(UserService userService) {
//        this.userService = userService;
//    }

@GetMapping("/login")
    public String login() {
    return "login";
}

@GetMapping("/")
    public String home(){
    return "index";
}




}



