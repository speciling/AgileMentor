package agilementor.sprint.entity;

import agilementor.sprint.dto.SprintResponse;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "sprint")
public class Sprint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sprint_id")
    private Long id;

    @Column(name = "project_id", nullable = false)
    private Long projectId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "goal")
    private String goal;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "is_done", nullable = false)
    private boolean isDone;

    @Column(name = "is_activate", nullable = false)
    private boolean isActivate;

    protected Sprint() {
    }

    public Sprint(Long projectId, String title, String goal, LocalDate startDate, LocalDate endDate,
        boolean isDone, boolean isActivate) {
        this.projectId = projectId;
        this.title = title;
        this.goal = goal;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isDone = isDone;
        this.isActivate = isActivate;
    }

    public void start() { this.isActivate = true; }

    public void complete() { this.isDone = true; }

    public Long getId() {
        return id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public String getTitle() {
        return title;
    }

    public String getGoal() {
        return goal;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public boolean isDone() {
        return isDone;
    }

    public boolean isActivate() { return isActivate; }


    public void update(String title, String goal, LocalDate startDate, LocalDate endDate) {
        this.title = title;
        this.goal = goal;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public SprintResponse toEntity() {
        return new SprintResponse(id, projectId, title, goal, startDate, endDate, isDone, isActivate);
    }
}