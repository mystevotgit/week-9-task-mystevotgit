package com.project.projectmanager.controllers;

import com.project.projectmanager.services.TeamService;
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
public class TeamsController {

    @Autowired
    TeamService teamService;

    @Autowired
    UserService userService;

    @GetMapping("/team")
    public String teams(HttpSession session, Model model) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }
        userService.FindUsers(session);
        teamService.FindTeamMembers(session);

        model.addAttribute("employees", session.getAttribute("employees"));
        model.addAttribute("teammembers", session.getAttribute("team"));
        model.addAttribute("user", session.getAttribute("user"));
        return "teams";
    }

    @PostMapping("/addteammember")
    public String addTeamMember(@RequestParam HashMap<String, String> formData, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }
        teamService.AddTeamMember(formData, session);
        return "redirect:/team";
    }

    @PostMapping("/removeteammember")
    public String removeMember(@RequestParam HashMap<String, String> formData, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }
        teamService.RemoveMembers(formData);
        return "redirect:/team";
    }

}
