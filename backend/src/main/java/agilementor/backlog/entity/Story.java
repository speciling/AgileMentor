package agilementor.backlog.entity;

import agilementor.project.entity.Project;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Story {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storyId;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    protected Story() {
    }

    public Story(Project project, String title, String description) {
        this.project = project;
        this.title = title;
        this.description = description;
    }

    public Long getStoryId() {
        return storyId;
    }

    public Project getProject() {
        return project;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void update(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
