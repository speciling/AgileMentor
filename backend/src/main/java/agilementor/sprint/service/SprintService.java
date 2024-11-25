package agilementor.sprint.service;

import agilementor.common.exception.EndDateNullException;
import agilementor.common.exception.ProjectNotFoundException;
import agilementor.common.exception.SprintNotFoundException;
import agilementor.project.entity.Project;
import agilementor.project.repository.ProjectMemberRepository;
import agilementor.project.repository.ProjectRespository;
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
    private final ProjectRespository projectRepository; // 추가: ProjectRepository
    private final ProjectMemberRepository projectMemberRepository; // 추가

    public SprintService(SprintRepository sprintRepository, ProjectRespository projectRepository,
        ProjectMemberRepository projectMemberRepository) {
        this.sprintRepository = sprintRepository;
        this.projectRepository = projectRepository;
        this.projectMemberRepository = projectMemberRepository;
    }

    // 프로젝트 멤버인지 확인하는 메서드
    private void validateProjectMember(Long memberId, Long projectId) {
        projectMemberRepository.findByMemberIdAndProjectId(memberId, projectId)
            .orElseThrow(ProjectNotFoundException::new);
    }

    // 스프린트 존재 확인 메서드
    private Sprint validateSprintExists(Long projectId, Long sprintId) {
        return sprintRepository.findByProject_ProjectIdAndId(projectId, sprintId)
            .orElseThrow(SprintNotFoundException::new);
    }

    public SprintResponse createSprint(Long memberId, Long projectId) {
        // 프로젝트 멤버인지 검증
        validateProjectMember(memberId, projectId);

        // Project 조회
        Project project = projectRepository.findById(projectId)
            .orElseThrow(ProjectNotFoundException::new);

        // 스프린트 개수 기반으로 제목 생성
        long sprintCount = sprintRepository.countByProject_ProjectId(projectId);
        String title = "Sprint " + (sprintCount + 1);

        Sprint sprint = new Sprint(project, title);
        return sprintRepository.save(sprint).toSprintResponse();
    }

    public List<SprintResponse> getAllSprints(Long memberId, Long projectId) {
        // 프로젝트 멤버인지 검증
        validateProjectMember(memberId, projectId);

        List<Sprint> sprints = sprintRepository.findByProject_ProjectId(projectId);
        return sprints.stream()
            .map(Sprint::toSprintResponse)
            .collect(Collectors.toList());
    }

    public SprintResponse getSprintById(Long memberId, Long projectId, Long sprintId) {
        // 프로젝트 멤버인지 검증
        validateProjectMember(memberId, projectId);
        // 스프린트 조회
        Sprint sprint = validateSprintExists(projectId, sprintId);
        return sprint.toSprintResponse();
    }

    public SprintResponse updateSprint(Long memberId, Long projectId, Long sprintId,
        SprintForm sprintForm) {
        // 프로젝트 멤버인지 검증
        validateProjectMember(memberId, projectId);
        // 스프린트 조회
        Sprint sprint = validateSprintExists(projectId, sprintId);
        // 업데이트 로직
        sprint.update(
            sprintForm.getTitle(),
            sprintForm.getGoal(),
            sprintForm.getEndDate(),
            sprint.isActivate() // 현재 활성화 상태 확인
        );

        // 저장 후 반환
        return sprintRepository.save(sprint).toSprintResponse();
    }

    public void deleteSprint(Long memberId, Long projectId, Long sprintId) {
        // 프로젝트 멤버인지 검증
        validateProjectMember(memberId, projectId);
        // 스프린트 조회
        Sprint sprint = validateSprintExists(projectId, sprintId);
        sprintRepository.delete(sprint);
    }

    public SprintResponse startSprint(Long memberId, Long projectId, Long sprintId,
        SprintForm sprintForm) {
        // 프로젝트 멤버인지 검증
        validateProjectMember(memberId, projectId);
        // 스프린트 조회
        Sprint sprint = validateSprintExists(projectId, sprintId);
        // 종료 날짜를 입력하지 않으면 예외 발생
        if (sprintForm.getEndDate() == null) {
            throw new EndDateNullException();
        }
        // 스프린트 시작
        sprint.start(); // isActivate를 true로 설정하고 시작날짜를 현재날짜로 설정

        // title, goal, endDate만 업데이트
        sprint.update(
            sprintForm.getTitle(),
            sprintForm.getGoal(),
            sprintForm.getEndDate(),
            true // 활성화 상태로 강제 설정
        );

        // 저장 후 반환
        return sprintRepository.save(sprint).toSprintResponse();
    }

    public SprintResponse completeSprint(Long memberId, Long projectId, Long sprintId) {
        // 프로젝트 멤버인지 검증
        validateProjectMember(memberId, projectId);
        // 스프린트 조회
        Sprint sprint = validateSprintExists(projectId, sprintId);
        sprint.complete(); // isDone 을 true 로 설정하고 종료날짜를 현재날짜로 설정
        return sprintRepository.save(sprint).toSprintResponse();
    }
}