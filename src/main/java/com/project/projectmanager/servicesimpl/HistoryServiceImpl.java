package com.project.projectmanager.servicesimpl;


import com.project.projectmanager.models.History;
import com.project.projectmanager.models.Issue;
import com.project.projectmanager.repository.HistoryRepository;
import com.project.projectmanager.repository.IssueRepository;
import com.project.projectmanager.services.HistoryService;
import com.project.projectmanager.util.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.*;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    HistoryRepository historyRepository;

    @Autowired
    IssueRepository issueRepository;

    @Override
    public void FindAllHistoryByProjectId(HttpSession session) {
        List<History> history = historyRepository.findByProjectId((Long) session.getAttribute("projectid"));
        Collections.reverse(history);
        session.setAttribute("history", history);
    }


    @Override
    public void SaveCreateStory(HashMap<String, String> formData, HttpSession session) {
        Long id = (Long) session.getAttribute("projectid");
        Helper helper = new Helper();
        History history = new History();
        history.setProjectId(id);
        history.setPriority(formData.get("priority"));
        history.setDescription(formData.get("description"));
        history.setDetails(formData.get("details"));
        history.setStatus("backlog");
        history.setEvent("created");
        history.setDate(helper.currentDate());
        historyRepository.save(history);
    }

    @Override
    public void SaveStatusUpdate(HashMap<String, String> formData) {
        History history = new History();
        Helper helper = new Helper();
        history.setIssueId(Long.parseLong(formData.get("storyid")));
        Optional<Issue> story = issueRepository.findById(history.getIssueId());
        Issue issue = story.get();
        history.setEvent("updated");
        history.setDescription(issue.getDescription());
        history.setDetails(issue.getDetails());
        history.setPriority(issue.getPriority());
        history.setProjectId(issue.getProjectId());
        history.setStatus(formData.get("status"));
        history.setDate(helper.currentDate());
        historyRepository.save(history);
    }

    @Override
    public void SaveStoryUpdate(HashMap<String, String> formData, HttpSession session) {
        Long id = Long.valueOf(formData.get("storyid"));
        Helper helper = new Helper();
        History history = new History();
        history.setIssueId(id);
        history.setProjectId((Long) session.getAttribute("projectid"));
        history.setPriority(formData.get("priority"));
        history.setDescription(formData.get("description"));
        history.setDetails(formData.get("details"));
        history.setStatus(formData.get("storystatus"));
        history.setDate(helper.currentDate());
        history.setEvent("Update");
        historyRepository.save(history);
    }

    @Override
    public void SaveDeletedStory(HashMap<String, String> formData, HttpSession session) {
        History history = new History();
        Helper helper = new Helper();
        history.setIssueId(Long.parseLong(formData.get("storyid")));
        Optional<Issue> story = issueRepository.findById(history.getIssueId());
        Issue issue = story.get();
        history.setEvent("deleted");
        history.setDescription(issue.getDescription());
        history.setDetails(issue.getDetails());
        history.setPriority(issue.getPriority());
        history.setProjectId(issue.getProjectId());
        history.setStatus(issue.getStatus());
        history.setDate(helper.currentDate());
        historyRepository.save(history);
    }
}