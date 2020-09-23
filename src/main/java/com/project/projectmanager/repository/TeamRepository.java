package com.project.projectmanager.repository;

import com.project.projectmanager.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findByProjectId(Long id);
}
