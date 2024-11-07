package agilementor.sprint.service;

import agilementor.sprint.dto.SprintForm;
import agilementor.sprint.dto.SprintResponse;
import agilementor.sprint.entity.Sprint;
import agilementor.sprint.repository.SprintRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class SprintService {

    private final SprintRepository sprintRepository;

    public SprintService(SprintRepository sprintRepository) {
        this.sprintRepository = sprintRepository;
    }

    public SprintResponse createSprint(Long projectId, SprintForm sprintForm) {
        // todo: project 객체 찾아 넣는걸로 바꾸기
        Sprint sprint = new Sprint(
            projectId,
            sprintForm.getTitle(),
            sprintForm.getGoal(),
            sprintForm.getStartDate(),
            sprintForm.getEndDate(),
            sprintForm.isDone()
        );
        return sprintRepository.save(sprint).toEntity();
    }

    public List<SprintResponse> getAllSprints(Long projectId) {
        List<Sprint> sprints = sprintRepository.findByProjectId(projectId);
        return sprints.stream()
            .map(Sprint::toEntity)
            .collect(Collectors.toList());
    }

    public SprintResponse getSprintById(Long projectId, Long sprintId) {
        Sprint sprint = sprintRepository.findByProjectIdAndId(projectId, sprintId)
            .orElseThrow(() -> new IllegalArgumentException("Sprint not found"));
        return sprint.toEntity();
    }

    public SprintResponse updateSprint(Long projectId, Long sprintId, SprintForm sprintForm) {
        // todo: 권한 체크하기
        Sprint sprint = getSprintByIdInternal(projectId, sprintId);
        sprint.update(
            sprintForm.getTitle(),
            sprintForm.getGoal(),
            sprintForm.getStartDate(),
            sprintForm.getEndDate(),
            sprintForm.isDone()
        );
        return sprintRepository.save(sprint).toEntity();
    }

    public void deleteSprint(Long projectId, Long sprintId) {
        Sprint sprint = getSprintByIdInternal(projectId, sprintId);
        sprintRepository.delete(sprint);
    }

    public SprintResponse completeSprint(Long projectId, Long sprintId) {
        Sprint sprint = getSprintByIdInternal(projectId, sprintId);
        sprint.update(
            sprint.getTitle(),
            sprint.getGoal(),
            sprint.getStartDate(),
            sprint.getEndDate(),
            true
        );
        return sprintRepository.save(sprint).toEntity();
    }

    private Sprint getSprintByIdInternal(Long projectId, Long sprintId) {
        return sprintRepository.findByProjectIdAndId(projectId, sprintId)
            .orElseThrow(() -> new IllegalArgumentException("Sprint not found"));
    }
}
