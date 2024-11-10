package agilementor.project.dto.request;

import agilementor.project.entity.Project;
import jakarta.validation.constraints.NotNull;

public record ProjectCreateRequest(
    @NotNull String title
) {

    public Project toEntity() {
        return new Project(title);
    }
}
