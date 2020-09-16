package com.project.projectmanager.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TeamProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long teamId;
    private long projectId;
    private String status;
    private String date;
}
