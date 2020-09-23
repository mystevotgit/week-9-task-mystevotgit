package com.project.projectmanager.controllers;

import com.project.projectmanager.models.User;
import com.project.projectmanager.services.HistoryService;
import com.project.projectmanager.services.IssueService;
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
public class ChartController {

    @Autowired
    IssueService issueService;

    @Autowired
    HistoryService historyService;


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
    public String openProjectChart(@RequestParam HashMap<String, String> formData, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }
        long projectid = Long.parseLong(formData.get("projectid"));
        session.setAttribute("projectid", projectid);
        return "redirect:/chart";
    }


    @PostMapping("/createStory")
    public String createStory(@RequestParam HashMap<String, String> formData, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }
        issueService.SaveStory(formData, session);
        historyService.SaveCreateStory(formData, session);
        return "redirect:/chart";
    }


    @PostMapping("/updateStoryStatus")
    public String updateStoryStatus(@RequestParam HashMap<String, String> formData, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }
        issueService.UpdateIssueStatus(formData);
        historyService.SaveStatusUpdate(formData);
        return "redirect:/chart";
    }


    @PostMapping("/openStory")
    public String openStory(@RequestParam HashMap<String, String> formData, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }
        long id = Long.parseLong(formData.get("storyid"));
        issueService.FindIssueById(id, session);
        return "redirect:/editStory";
    }


    @GetMapping("/editStory")
    public String editStory(Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("editstory", session.getAttribute("currentStory"));
        return "editstory";
    }

    @PostMapping("/updateStory")
    public String updateStory(@RequestParam HashMap<String, String> formData, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }
        issueService.UpdateStory(formData, session);
        historyService.SaveStoryUpdate(formData, session);
        return "redirect:/chart";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam HashMap<String, String> formData, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }
        historyService.SaveDeletedStory(formData, session);
        issueService.DeleteStory(formData, session);
        return "redirect:/chart";
    }
}
