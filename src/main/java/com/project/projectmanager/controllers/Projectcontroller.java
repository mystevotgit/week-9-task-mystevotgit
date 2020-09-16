package com.project.projectmanager.controllers;

import com.project.projectmanager.models.User;
import com.project.projectmanager.services.ProjectService;
import com.project.projectmanager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;

@Controller
public class Projectcontroller {

    @Autowired
    UserService userService;

    @Autowired
    ProjectService projectService;


    @PostMapping("/projects")
    public String login(@RequestParam HashMap<String, String> formData, @Valid User user, HttpSession session) {
        userService.FindUserData(formData, session);
        return "redirect:/projects";
    }

    @GetMapping("/projects")
    public String viewProjects(@Valid User user, HttpSession session, Model model){
        model.addAttribute("user", session.getAttribute("user"));
//        projectService.FindAllProjects(session);
//        model.addAttribute("teamprojects", session.getAttribute("teamprojects"));
        projectService.FindProjectByLeadId(session);
        model.addAttribute("leadprojects", session.getAttribute("leadprojects"));
        return "projects";
    }

    @PostMapping("/createproject")
    public String createProject(@RequestParam HashMap<String, String> formData, @Valid User user, HttpSession session) {
        projectService.SaveProject(formData, session);
        return "redirect:/projects";
    }

}
