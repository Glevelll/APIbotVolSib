package com.example.VSAPIBot.Response;

import com.example.VSAPIBot.Project.Project;
import jakarta.persistence.*;

@Entity
@Table(name = "response")
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idResponse")
    private Long id;
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "idProject")
    private Project project;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
