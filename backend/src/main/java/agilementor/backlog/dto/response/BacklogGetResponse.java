package agilementor.backlog.dto.response;

import agilementor.backlog.entity.Backlog;
import agilementor.backlog.entity.Priority;
import agilementor.backlog.entity.Status;

public record BacklogGetResponse(
    Long backlogId,
    Long projectId,
    Long sprintId,
    Long storyId,
    Long memberId,
    String title,
    String description,
    Status status,
    Priority priority
) {

    public static BacklogGetResponse from(Backlog backlog) {
        Long backlogId = backlog.getBacklogId();
        Long projectId = backlog.getProject().getProjectId();
        Long sprintId = null;
        Long storyId = null;
        Long memberId = null;
        if (backlog.getSprint() != null) {
            sprintId = backlog.getSprint().getId();
        }
        if (backlog.getStory() != null) {
            storyId = backlog.getStory().getStoryId();
        }
        if (backlog.getAssignee() != null) {
            memberId = backlog.getAssignee().getMemberId();
        }

        return new BacklogGetResponse(backlogId, projectId, sprintId, storyId, memberId,
            backlog.getTitle(), backlog.getDescription(), backlog.getStatus(),
            backlog.getPriority());
    }

}
