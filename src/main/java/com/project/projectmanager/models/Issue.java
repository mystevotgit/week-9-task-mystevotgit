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
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long projectId;
    private long title;
    private String description;
    private String details;
    private Integer priority;
    private String status;
    private String date;

    @OneToMany
    private List<Task> tasks = new ArrayList<>();
}
