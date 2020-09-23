package com.project.projectmanager.controllers;

import com.project.projectmanager.services.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HistoryController {

    @Autowired
    HistoryService historyService;

    @GetMapping("/history")
    public String history(HttpSession session, Model model) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }
        historyService.FindAllHistoryByProjectId(session);
        model.addAttribute("history", session.getAttribute("history"));
        model.addAttribute("user", session.getAttribute("user"));
        return "history";
    }
}
