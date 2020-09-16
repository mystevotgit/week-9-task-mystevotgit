package com.project.projectmanager.servicesimpl;

import com.project.projectmanager.models.TeamProject;
import com.project.projectmanager.repository.TeamProjectRepository;
import com.project.projectmanager.services.TeamProjectService;
import com.project.projectmanager.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamProjectServiceImpl implements TeamProjectService {

    @Autowired
    TeamProjectRepository teamProjectRepository;

    @Override
    public TeamProject FindProjectByteamId(long teamId) {
        return teamProjectRepository.findByteamId(teamId);
    }
}
