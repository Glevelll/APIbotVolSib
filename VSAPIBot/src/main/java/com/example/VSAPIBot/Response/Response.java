package com.example.VSAPIBot.Response;

import com.example.VSAPIBot.Project.Project;
import jakarta.persistence.*;

@Entity
@Table(name = "response")
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String loginMember;
    private String description;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginMember() {
        return loginMember;
    }

    public void setLoginMember(String loginMember) {
        this.loginMember = loginMember;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
