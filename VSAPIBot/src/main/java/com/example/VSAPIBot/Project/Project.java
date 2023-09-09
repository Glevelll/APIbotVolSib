package com.example.VSAPIBot.Project;

import com.example.VSAPIBot.Moderator.Moderator;
import com.example.VSAPIBot.Manager.Manager;
import jakarta.persistence.*;


@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProject")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "type")
    private String type;
    @Column(name = "description")
    private String description;
    @Column(name = "requirements")
    private String requirements;
    @Column(name = "stateProject")
    private String state;
    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "loginManager")
    private Manager infoManager;

    @ManyToOne
    @JoinColumn(name = "loginModerator")
    private Moderator infoModer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Manager getInfoManager() {
        return infoManager;
    }

    public void setInfoManager(Manager infoManager) {
        this.infoManager = infoManager;
    }

    public Moderator getInfoModer() {
        return infoModer;
    }

    public void setInfoModer(Moderator infoModer) {
        this.infoModer = infoModer;
    }
}
