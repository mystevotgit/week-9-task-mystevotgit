package com.project.projectmanager.repository;

import com.project.projectmanager.models.TeamProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamProjectRepository extends JpaRepository<TeamProject, Long> {
    TeamProject findByteamId(long teamId);
}
