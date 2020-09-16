package com.project.projectmanager.controllers;

import com.project.projectmanager.models.User;
import com.project.projectmanager.services.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;

@Controller
public class ChartController {

    @Autowired
    IssueService issueService;

    @GetMapping("/chart")
    public String openProjectChart(@RequestParam HashMap<String, String> formData, @Valid User user, HttpSession session, Model model) {
        issueService.FindallIssuesByProjectId(formData, session);
        model.addAttribute("project", session.getAttribute("currentProject"));
        model.addAttribute("user", session.getAttribute("user"));
        return "chart";
    }
}
