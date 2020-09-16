package com.project.projectmanager.servicesimpl;

import com.project.projectmanager.models.Issue;
import com.project.projectmanager.models.Project;
import com.project.projectmanager.repository.IssueRepository;
import com.project.projectmanager.services.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Service
public class IssueServiceImpl implements IssueService {

    @Autowired
    IssueRepository issueRepository;

    @Override
    public void FindallIssuesByProjectId(HashMap<String, String> formData, HttpSession session) {
        Project project = new Project();
        project.setId(Long.parseLong(formData.get("projectid")));
        List<Issue> userStories = issueRepository.findAllByProjectId(project.getId());
        project.setStories(userStories);
        session.setAttribute("currentProject", project);
    }
}
