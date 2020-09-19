package com.project.projectmanager.controllers;

import com.project.projectmanager.models.User;
import com.project.projectmanager.services.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ChartController {

    @Autowired
    IssueService issueService;


    @GetMapping("/chart")
    public String projectChart(Model model, HttpSession session) {
        issueService.FindAllIssuesByProjectId(session);
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("backlog", session.getAttribute("backlog"));
        model.addAttribute("progress", session.getAttribute("progress"));
        model.addAttribute("review", session.getAttribute("review"));
        model.addAttribute("done", session.getAttribute("done"));
        return "chart";
    }

    @PostMapping("/chart")
    public String openProjectChart(@RequestParam HashMap<String, String> formData, @Valid User user, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }
        long projectid = Long.parseLong(formData.get("projectid"));
        session.setAttribute("projectid", projectid);
        return "redirect:/chart";
    }


    @PostMapping("/createStory")
    public String createStory(@RequestParam HashMap<String, String> formData, @Valid User user, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }
        issueService.SaveStory(formData, session);
        return "redirect:/chart";
    }


    @PostMapping("/updateStoryStatus")
    public String updateStoryStatus(@RequestParam HashMap<String, String> formData, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }
        issueService.UpdateIssueStatus(formData);
        return "redirect:/chart";
    }
}
