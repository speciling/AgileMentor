package agilementor.backlog.dto.request;

import agilementor.backlog.entity.Story;
import agilementor.project.entity.Project;
import jakarta.validation.constraints.NotNull;

public record StoryUpdateRequest(
    @NotNull String title,
    @NotNull String description
) {

    public Story toEntity(Project project) {
        return new Story(project, title, description);
    }
}
