package com.project.projectmanager.services;

import com.project.projectmanager.models.TeamProject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeamProjectService {
    TeamProject FindProjectByteamId(long teamId);
}
