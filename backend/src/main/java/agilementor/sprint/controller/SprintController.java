package agilementor.sprint.controller;

import agilementor.sprint.dto.SprintForm;
import agilementor.sprint.dto.SprintResponse;
import agilementor.sprint.service.SprintService;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/projects/{projectId}/sprints")
public class SprintController {

    private final SprintService sprintService;

    public SprintController(SprintService sprintService) {
        this.sprintService = sprintService;
    }

    @PostMapping
    public SprintResponse createSprint(@PathVariable Long projectId,
        @RequestBody SprintForm sprintForm) {
        return sprintService.createSprint(projectId, sprintForm);
    }

    @GetMapping
    public List<SprintResponse> getAllSprints(@PathVariable Long projectId) {
        return sprintService.getAllSprints(projectId);
    }

    @GetMapping("/{sprintId}")
    public SprintResponse getSprintById(@PathVariable Long projectId, @PathVariable Long sprintId) {
        return sprintService.getSprintById(projectId, sprintId);
    }

    @PutMapping("/{sprintId}")
    public SprintResponse updateSprint(@PathVariable Long projectId, @PathVariable Long sprintId,
        @RequestBody SprintForm sprintForm) {
        return sprintService.updateSprint(projectId, sprintId, sprintForm);
    }

    @DeleteMapping("/{sprintId}")
    public void deleteSprint(@PathVariable Long projectId, @PathVariable Long sprintId) {
        sprintService.deleteSprint(projectId, sprintId);
    }

    // startSprint 메서드 수정: 네 개의 필드 업데이트를 위한 SprintForm 추가
    @PutMapping("/{sprintId}/start")
    public SprintResponse startSprint(@PathVariable Long projectId, @PathVariable Long sprintId, @RequestBody SprintForm sprintForm) {
        return sprintService.startSprint(projectId, sprintId, sprintForm);
    }

    @PutMapping("/{sprintId}/complete")
    public SprintResponse completeSprint(@PathVariable Long projectId,
        @PathVariable Long sprintId) {
        return sprintService.completeSprint(projectId, sprintId);
    }
}
