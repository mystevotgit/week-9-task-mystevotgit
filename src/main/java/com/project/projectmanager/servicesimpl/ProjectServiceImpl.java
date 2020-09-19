package com.project.projectmanager.servicesimpl;

import com.project.projectmanager.models.Project;
import com.project.projectmanager.models.User;
import com.project.projectmanager.repository.ProjectRepository;
import com.project.projectmanager.services.ProjectService;
import com.project.projectmanager.services.TeamProjectService;
import com.project.projectmanager.util.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    TeamProjectService teamProjectService;

    @Autowired
    ProjectRepository projectRepository;

//    @Override
//    public void FindAllProjects(HttpSession session) {
//        User user = (User) session.getAttribute("user");
//        Collection<? extends TeamMember> team = teamMemberService.FindUserTeam(user.getId());
//        user.getTeams().add();
//        List<TeamProject> projects = new ArrayList<>();
//        if (!user.getTeams().isEmpty()) {
//            user.getTeams().forEach(team -> projects.add(teamProjectService.FindProjectByteamId(team.getTeamId())));
//            session.setAttribute("teamprojects", projects);
//        }
//
//    }

    @Override
    public void FindProjectByLeadId(HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Project> leadProjects = projectRepository.findAllByUserId(user.getId());
        if (!leadProjects.isEmpty()) {
            session.setAttribute("leadprojects", leadProjects);
        }
    }

    @Override
    public void SaveProject(HashMap<String, String> formData, HttpSession session) {
        Project project = new Project();
        project.setName(formData.get("projectname"));
        Helper helper = new Helper();
        project.setDate(helper.currentDate());
        User user = (User) session.getAttribute("user");
        project.setUserId(user.getId());
        projectRepository.save(project);
    }
}
