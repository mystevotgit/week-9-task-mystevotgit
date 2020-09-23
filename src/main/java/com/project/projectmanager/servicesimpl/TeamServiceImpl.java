package com.project.projectmanager.servicesimpl;

import com.project.projectmanager.models.Team;
import com.project.projectmanager.models.User;
import com.project.projectmanager.repository.TeamRepository;
import com.project.projectmanager.repository.UserRepository;
import com.project.projectmanager.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public void FindTeamMembers(HttpSession session) {
        Long id = (Long) session.getAttribute("projectid");
        Team team = teamRepository.findByProjectId(id);
        List<User> users = (List<User>) session.getAttribute("employees");
        users.forEach(user -> {
//            teamRepository.findByUserId(user.getId());

        });
        session.setAttribute("team", team);
    }

    @Override
    public void AddTeamMember(HashMap<String, String> formData, HttpSession session) {
        Long id = (Long) session.getAttribute("projectid");
        Optional<User> employee = userRepository.findById(Long.valueOf(formData.get("employeeid")));
        User user = employee.get();
        Team teammember = new Team();
        teammember.setProjectId(id);
        teammember.setUserid(user.getId());
        teammember.setFirstname(user.getFirstname());
        teammember.setLastname(user.getLastname());
        teammember.setGender(user.getGender());
        teammember.setRole(user.getRole());
        teamRepository.save(teammember);
    }

    @Override
    public void RemoveMembers(HashMap<String, String> formData) {
        teamRepository.deleteById(Long.valueOf(formData.get("memberid")));
    }
}
