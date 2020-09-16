package com.project.projectmanager.servicesimpl;

import com.project.projectmanager.models.Project;
import com.project.projectmanager.models.User;
import com.project.projectmanager.repository.ProjectRepository;
import com.project.projectmanager.services.ProjectService;
import com.project.projectmanager.services.TeamProjectService;
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
        Date date = new Date();
        String res = "";
        String str = String.valueOf(date);
        List<String> list = Arrays.asList(str.split(" "));
        res += list.get(list.size()-1);
        res += "-";
        String month;
        String val = list.get(1);
        switch (val) {
            case "Jan":
                month = "01";
                break;
            case "Feb":
                month = "02";
                break;
            case "Mar":
                month = "03";
                break;
            case "Apr":
                month = "04";
                break;
            case "May":
                month = "05";
                break;
            case "Jun":
                month = "06";
                break;
            case "Jul":
                month = "07";
                break;
            case "Aug":
                month = "08";
                break;
            case "Sep":
                month = "09";
                break;
            case "Oct":
                month = "10";
                break;
            case "Nov":
                month = "11";
                break;
            case "Dec":
                month = "12";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + val);
        }
        res += month;
        res += "-";
        res += list.get(2);
        project.setDate(res);
        User user = (User) session.getAttribute("user");
        project.setUserId(user.getId());
        projectRepository.save(project);
    }
}
