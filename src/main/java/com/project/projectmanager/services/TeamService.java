package com.project.projectmanager.services;

import com.project.projectmanager.models.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Service
public interface TeamService {
    void FindTeamMembers(HttpSession session);

    void AddTeamMember(HashMap<String, String> formData, HttpSession session);

    void RemoveMembers(HashMap<String, String> formData);
}
