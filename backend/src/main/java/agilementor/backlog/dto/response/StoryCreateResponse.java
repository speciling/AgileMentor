package agilementor.backlog.dto.response;

import agilementor.backlog.entity.Status;
import agilementor.backlog.entity.Story;

public record StoryCreateResponse(
    Long storyId,
    Long projectId,
    String title,
    String description,
    Status status
) {

    public static StoryCreateResponse from(Story story) {
        return new StoryCreateResponse(story.getStoryId(), story.getProject().getProjectId(),
            story.getTitle(), story.getDescription(), Status.IN_PROGRESS);
    }

}
