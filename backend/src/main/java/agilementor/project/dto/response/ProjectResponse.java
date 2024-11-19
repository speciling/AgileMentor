package agilementor.project.dto.response;

import agilementor.project.entity.Project;
import jakarta.validation.constraints.NotNull;

public record ProjectResponse(
    @NotNull Long projectId,
    @NotNull String title
) {

    public static ProjectResponse from(Project project) {
        return new ProjectResponse(project.getProjectId(), project.getTitle());
    }
}
