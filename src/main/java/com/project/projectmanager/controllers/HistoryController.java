package com.project.projectmanager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HistoryController {

    @GetMapping("/history")
    public String history(HttpSession session, Model model) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }
        model.addAttribute("user", session.getAttribute("user"));
        return "history";
    }
}
