package agilementor.project.dto.request;

import jakarta.validation.constraints.NotNull;

public record ProjectUpdateRequest(
    @NotNull String title
) {

}
