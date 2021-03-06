package com.project.projectmanager.servicesimpl;

import com.project.projectmanager.models.Issue;
import com.project.projectmanager.models.Project;
import com.project.projectmanager.repository.IssueRepository;
import com.project.projectmanager.services.IssueService;
import com.project.projectmanager.util.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class IssueServiceImpl implements IssueService {

    @Autowired
    IssueRepository issueRepository;

    @Override
    public void FindAllIssuesByProjectId(HttpSession session) {
        Project projectBacklog = new Project();
        Project projectProgress = new Project();
        Project projectReview = new Project();
        Project projectDone = new Project();
        Long id = (Long) session.getAttribute("projectid");
        if (id != null) {
            projectBacklog.setId(id);
            projectProgress.setId(id);
            projectReview.setId(id);
            projectDone.setId(id);
            List<Issue> userStories = issueRepository.findByProjectId(id);
            List<Issue> backlog = new ArrayList<>();
            List<Issue> progress = new ArrayList<>();
            List<Issue> review = new ArrayList<>();
            List<Issue> done = new ArrayList<>();
            userStories.forEach(story -> {
                if (story.getStatus().equals("backlog")) {
                    backlog.add(story);
                }else if (story.getStatus().equals("in progress")) {
                    progress.add(story);
                }else if (story.getStatus().equals("review")) {
                    review.add(story);
                }else if (story.getStatus().equals("done")) {
                    done.add(story);
                }
            });
            projectBacklog.setStories(backlog);
            projectProgress.setStories(progress);
            projectReview.setStories(review);
            projectDone.setStories(done);
            session.setAttribute("backlog", projectBacklog);
            session.setAttribute("progress", projectProgress);
            session.setAttribute("review", projectReview);
            session.setAttribute("done", projectDone);
        }
    }

    @Override
    public void SaveStory(HashMap<String, String> formData, HttpSession session) {
        Long id = (Long) session.getAttribute("projectid");
        Helper helper = new Helper();
        Issue issue = new Issue();
        issue.setProjectId(id);
        issue.setPriority(formData.get("priority"));
        issue.setDescription(formData.get("description"));
        issue.setDetails(formData.get("details"));
        issue.setStatus("backlog");
        issue.setDate(helper.currentDate());
        issueRepository.save(issue);
    }

    @Override
    public void UpdateIssueStatus(HashMap<String, String> formData) {
        Issue issue = new Issue();
        issue.setId(Long.parseLong(formData.get("storyid")));
        Optional<Issue> story = issueRepository.findById(issue.getId());
        issue = story.get();
        issue.setStatus(formData.get("status"));
        issueRepository.save(issue);
    }

    @Override
    public void FindIssueById(long id, HttpSession session) {
        Issue issue;
        Optional<Issue> story = issueRepository.findById(id);
        issue = story.get();
        session.setAttribute("currentStory", issue);
    }

    @Override
    public void UpdateStory(HashMap<String, String> formData, HttpSession session) {
        Long id = Long.valueOf(formData.get("storyid"));
        Helper helper = new Helper();
        Issue issue = new Issue();
        issue.setId(id);
        issue.setProjectId((Long) session.getAttribute("projectid"));
        issue.setPriority(formData.get("priority"));
        issue.setDescription(formData.get("description"));
        issue.setDetails(formData.get("details"));
        issue.setStatus(formData.get("storystatus"));
        issue.setDate(helper.currentDate());
        issueRepository.save(issue);
    }

    @Override
    public void DeleteStory(HashMap<String, String> formData, HttpSession session) {
        Long id = Long.valueOf(formData.get("storyid"));
        issueRepository.deleteById(id);
    }
}
