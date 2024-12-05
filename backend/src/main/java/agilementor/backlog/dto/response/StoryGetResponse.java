package agilementor.backlog.dto.response;

import agilementor.backlog.entity.Status;
import agilementor.backlog.entity.Story;

public record StoryGetResponse(
    Long storyId,
    Long projectId,
    String title,
    String description,
    Status status
) {

    public static StoryGetResponse from(Story story, Status status) {
        return new StoryGetResponse(story.getStoryId(), story.getProject().getProjectId(),
            story.getTitle(), story.getDescription(), status);
    }

}
