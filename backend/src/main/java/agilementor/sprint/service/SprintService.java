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
            sprintForm.isDone(),
            sprintForm.isActivate()
        );
        return sprintRepository.save(sprint).toSprintResponse();
    }

    public List<SprintResponse> getAllSprints(Long projectId) {
        List<Sprint> sprints = sprintRepository.findByProjectId(projectId);
        return sprints.stream()
            .map(Sprint::toSprintResponse)
            .collect(Collectors.toList());
    }

    public SprintResponse getSprintById(Long projectId, Long sprintId) {
        Sprint sprint = sprintRepository.findByProjectIdAndId(projectId, sprintId)
            .orElseThrow(() -> new IllegalArgumentException("Sprint not found"));
        return sprint.toSprintResponse();
    }

    public SprintResponse updateSprint(Long projectId, Long sprintId, SprintForm sprintForm) {
        // todo: 권한 체크하기
        Sprint sprint = getSprintByIdInternal(projectId, sprintId);
        sprint.update(
            sprintForm.getTitle(),
            sprintForm.getGoal(),
            sprintForm.getStartDate(),
            sprintForm.getEndDate()
        );
        return sprintRepository.save(sprint).toSprintResponse();
    }

    public void deleteSprint(Long projectId, Long sprintId) {
        Sprint sprint = getSprintByIdInternal(projectId, sprintId);
        sprintRepository.delete(sprint);
    }

    public SprintResponse startSprint(Long projectId, Long sprintId, SprintForm sprintForm) {
        Sprint sprint = getSprintByIdInternal(projectId, sprintId);

        // 스프린트 활성화 및 네 개의 필드 업데이트
        sprint.start(); // isActivate를 true로 설정
        sprint.update(
            sprintForm.getTitle(),
            sprintForm.getGoal(),
            sprintForm.getStartDate(),
            sprintForm.getEndDate()
        );

        return sprintRepository.save(sprint).toSprintResponse();
    }

    public SprintResponse completeSprint(Long projectId, Long sprintId) {
        Sprint sprint = getSprintByIdInternal(projectId, sprintId);
        sprint.complete();
        return sprintRepository.save(sprint).toSprintResponse();
    }

    private Sprint getSprintByIdInternal(Long projectId, Long sprintId) {
        return sprintRepository.findByProjectIdAndId(projectId, sprintId)
            .orElseThrow(() -> new IllegalArgumentException("Sprint not found"));
    }
}
