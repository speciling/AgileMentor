package agilementor.backlog.dto.response;

import agilementor.backlog.entity.Status;
import agilementor.backlog.entity.Story;

public record StoryUpdateResponse(
    Long storyId,
    Long projectId,
    String title,
    String description,
    Status status
) {

    public static StoryUpdateResponse from(Story story, Status status) {
        return new StoryUpdateResponse(story.getStoryId(), story.getProject().getProjectId(),
            story.getTitle(), story.getDescription(), status);
    }

}
