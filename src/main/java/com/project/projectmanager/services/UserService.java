package com.project.projectmanager.services;

import com.project.projectmanager.models.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Service
public interface UserService {
    void SaveUserData(HashMap<String, String> formData, User user);
    void FindUserData(HashMap<String, String> email, HttpSession session);
}
