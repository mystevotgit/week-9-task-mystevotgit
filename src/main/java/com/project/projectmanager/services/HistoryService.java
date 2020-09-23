package com.project.projectmanager.services;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Service
public interface HistoryService {
    void FindAllHistoryByProjectId(HttpSession session);

    void SaveCreateStory(HashMap<String, String> formData, HttpSession session);

    void SaveStatusUpdate(HashMap<String, String> formData);

    void SaveStoryUpdate(HashMap<String, String> formData, HttpSession session);

    void SaveDeletedStory(HashMap<String, String> formData, HttpSession session);
}
