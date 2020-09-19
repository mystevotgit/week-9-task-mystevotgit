package com.project.projectmanager.services;

import com.project.projectmanager.models.Issue;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Service
public interface IssueService {
    void FindAllIssuesByProjectId(HttpSession session);

    void SaveStory(HashMap<String, String> formData, HttpSession session);

    void UpdateIssueStatus(HashMap<String, String> formData);
}
