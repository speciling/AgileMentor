package agilementor.sprint.dto;

import java.time.LocalDate;

public class SprintForm {

    private final String title;
    private final String goal;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final boolean isDone;
    private final boolean isActivate;

    // 모든 필드를 초기화하는 생성자 추가
    public SprintForm(String title, String goal, LocalDate startDate, LocalDate endDate,
        boolean isDone, boolean isActivate) {
        this.title = title;
        this.goal = goal;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isDone = isDone;
        this.isActivate = isActivate;
    }

    // Getters
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
}
