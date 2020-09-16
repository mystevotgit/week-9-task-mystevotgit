package com.project.projectmanager.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String date;

//    @OneToMany
//    private List<TeamMember> members = new ArrayList<>();

    @OneToOne
    private TeamProject projects = new TeamProject();
}
