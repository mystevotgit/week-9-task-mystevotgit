package com.project.projectmanager.services;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Service
public interface IssueService {
    void FindallIssuesByProjectId(HashMap<String, String> formData, HttpSession session);
}
