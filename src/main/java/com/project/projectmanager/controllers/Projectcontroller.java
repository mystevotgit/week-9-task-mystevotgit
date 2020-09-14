package com.project.projectmanager.controllers;

import com.project.projectmanager.models.User;
import com.project.projectmanager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;

@Controller
public class Projectcontroller {

    @Autowired
    UserService userService;


    @PostMapping("/projects")
    public String login(@RequestParam HashMap<String, String> formData, @Valid User user, HttpSession session, Model model) {
        userService.FindUserData(formData, session);
        model.addAttribute("user", session.getAttribute("user"));
        return "projects";
    }


}
