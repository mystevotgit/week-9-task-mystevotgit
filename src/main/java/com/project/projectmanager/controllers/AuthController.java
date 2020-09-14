package com.project.projectmanager.controllers;

import com.project.projectmanager.models.User;
import com.project.projectmanager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//import org.springframework.validation.BindingResult;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;

@Controller
public class AuthController {


    @Autowired
    UserService userService;

//    private User user;
//    private Model model;
//    private BindingResult result;

    @GetMapping("/")
    public String signup_Login (RedirectAttributes redirectAttributes) {
        redirectAttributes.getFlashAttributes();
        return "index";
    }

    @PostMapping("/register")
    public String addUser(@RequestParam HashMap<String, String> formData, @Valid User user, RedirectAttributes redirectAttributes) {
        try {
            userService.SaveUserData(formData, user);
            redirectAttributes.addFlashAttribute("msg", "You have successfully registered.");
        } catch(RemoteAccessException e) {
            redirectAttributes.addFlashAttribute("msg", e.getMessage());
        }
        return "redirect:/";
    }

    /**
     * This method logs a user out.
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logOut(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}
