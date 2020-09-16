package com.project.projectmanager.services;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Service
public interface ProjectService {
//    void FindAllProjects(HttpSession session);

    void FindProjectByLeadId(HttpSession session);

    void SaveProject(HashMap<String, String> formData, HttpSession session);
}
