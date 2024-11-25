package agilementor.sprint.entity;

import agilementor.project.entity.Project;
import agilementor.sprint.dto.SprintResponse;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "sprint")
public class Sprint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sprint_id")
    private Long id;

    @ManyToOne // Many Sprints can belong to one Project
    @JoinColumn(name = "project_id", nullable = false)
    private Project project; // Project 객체로 연관관계 설정

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

    public Sprint(Project project, String title, String goal, LocalDate startDate,
        LocalDate endDate,
        boolean isDone, boolean isActivate) {
        this.project = project;
        this.title = title;
        this.goal = goal;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isDone = isDone;
        this.isActivate = isActivate;
    }

    // 추가된 간단한 생성자
    public Sprint(Project project, String title) {
        this.project = project;
        this.title = title;
        this.goal = null; // 기본값 null
        this.startDate = null; // 기본값 null
        this.endDate = null; // 기본값 null
        this.isDone = false; // 기본값 false
        this.isActivate = false; // 기본값 false
    }

    // start 메서드: isActivate를 true로 설정하고 startDate를 현재 날짜로 설정
    public void start() {
        this.isActivate = true;
        this.startDate = LocalDate.now(); // 현재 날짜로 설정
    }

    // complete 메서드: isDone을 true로 설정하고 endDate를 현재 날짜로 설정
    public void complete() {
        this.isDone = true;
        this.endDate = LocalDate.now(); // 현재 날짜로 설정
    }

    public Long getId() {
        return id;
    }

    public Project getProject() {
        return project;
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

    public boolean isActivate() {
        return isActivate;
    }


    public void update(String title, String goal, LocalDate endDate, boolean isActivate) {
        if (isActivate) {
            // 활성화 상태에서는 title, goal, endDate만 변경 가능
            if (title != null) {
                this.title = title;
            }
            if (goal != null) {
                this.goal = goal;
            }
            if (endDate != null) {
                this.endDate = endDate;
            }
        } else {
            // 비활성화 상태에서는 title과 goal만 변경 가능
            if (title != null) {
                this.title = title;
            }
            if (goal != null) {
                this.goal = goal;
            }
        }
    }


    public SprintResponse toSprintResponse() {
        return new SprintResponse(id, project.getProjectId(), title, goal, startDate, endDate,
            isDone,
            isActivate);
    }
}