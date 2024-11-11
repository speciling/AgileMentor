package agilementor.sprint.dto;

import java.time.LocalDate;

public record SprintResponse(
    Long id,
    Long projectId,
    String title,
    String goal,
    LocalDate startDate,
    LocalDate endDate,
    boolean isDone,
    boolean isActivate // 새로운 필드 추가
) {

}
