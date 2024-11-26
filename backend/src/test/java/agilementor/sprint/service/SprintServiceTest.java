package agilementor.sprint.service;

import agilementor.common.exception.EndDateNullException;
import agilementor.common.exception.ProjectNotFoundException;
import agilementor.common.exception.SprintNotFoundException;
import agilementor.common.exception.TitleNullException;
import agilementor.member.entity.Member;
import agilementor.project.entity.Project;
import agilementor.project.entity.ProjectMember;
import agilementor.project.repository.ProjectMemberRepository;
import agilementor.project.repository.ProjectRespository;
import agilementor.sprint.dto.SprintForm;
import agilementor.sprint.dto.SprintResponse;
import agilementor.sprint.entity.Sprint;
import agilementor.sprint.repository.SprintRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

class SprintServiceTest {

    @Mock
    private SprintRepository sprintRepository;

    @Mock
    private ProjectRespository projectRepository;

    @Mock
    private ProjectMemberRepository projectMemberRepository;

    @InjectMocks
    private SprintService sprintService;

    private final Long memberId = 1L;
    private final Long projectId = 1L;
    private final Long sprintId = 1L;

    private Project project;
    private Sprint sprint;
    private ProjectMember projectMember;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mock Data Setup
        project = new Project("Test Project");
        ReflectionTestUtils.setField(project, "projectId", projectId);

        sprint = new Sprint(project, "Sprint 1");
        ReflectionTestUtils.setField(sprint, "id", sprintId);

        projectMember = new ProjectMember(project, new Member("test@example.com", "Test User", "pic.jpg"), true);
        ReflectionTestUtils.setField(projectMember.getMember(), "memberId", memberId);
    }

    @Test
    @DisplayName("스프린트를 생성한다.")
    void createSprint() {
        // given
        given(projectMemberRepository.findByMemberIdAndProjectId(any(), any())).willReturn(Optional.of(projectMember));
        given(projectRepository.findById(any())).willReturn(Optional.of(project));
        given(sprintRepository.countByProject_ProjectId(any())).willReturn(0L);
        given(sprintRepository.save(any(Sprint.class))).willReturn(sprint);

        // when
        SprintResponse response = sprintService.createSprint(memberId, projectId);

        // then
        assertThat(response.title()).isEqualTo("Sprint 1");
        then(sprintRepository).should().save(any(Sprint.class));
    }

    @Test
    @DisplayName("프로젝트 ID로 모든 스프린트를 조회한다.")
    void getAllSprints() {
        // given
        given(projectMemberRepository.findByMemberIdAndProjectId(any(), any())).willReturn(Optional.of(projectMember));
        given(sprintRepository.findByProject_ProjectId(any())).willReturn(List.of(sprint));

        // when
        List<SprintResponse> responseList = sprintService.getAllSprints(memberId, projectId);

        // then
        assertThat(responseList).hasSize(1);
        assertThat(responseList.get(0).title()).isEqualTo("Sprint 1");
    }

    @Test
    @DisplayName("스프린트 ID로 스프린트를 조회한다.")
    void getSprintById() {
        // given
        given(projectMemberRepository.findByMemberIdAndProjectId(any(), any())).willReturn(Optional.of(projectMember));
        given(sprintRepository.findByProject_ProjectIdAndId(any(), any())).willReturn(Optional.of(sprint));

        // when
        SprintResponse response = sprintService.getSprintById(memberId, projectId, sprintId);

        // then
        assertThat(response.title()).isEqualTo("Sprint 1");
    }

    @Test
    @DisplayName("스프린트 정보를 업데이트한다.")
    void updateSprint() {
        // given
        SprintForm form = new SprintForm("Updated Title", "Updated Goal", LocalDate.now(), LocalDate.now().plusDays(7), false, false);
        given(projectMemberRepository.findByMemberIdAndProjectId(any(), any())).willReturn(Optional.of(projectMember));
        given(sprintRepository.findByProject_ProjectIdAndId(any(), any())).willReturn(Optional.of(sprint));
        given(sprintRepository.save(any(Sprint.class))).willReturn(sprint);

        // when
        SprintResponse response = sprintService.updateSprint(memberId, projectId, sprintId, form);

        // then
        assertThat(response.title()).isEqualTo("Updated Title");
        assertThat(response.goal()).isEqualTo("Updated Goal");
    }

    @Test
    @DisplayName("스프린트를 삭제한다.")
    void deleteSprint() {
        // given
        given(projectMemberRepository.findByMemberIdAndProjectId(any(), any())).willReturn(Optional.of(projectMember));
        given(sprintRepository.findByProject_ProjectIdAndId(any(), any())).willReturn(Optional.of(sprint));

        // when
        sprintService.deleteSprint(memberId, projectId, sprintId);

        // then
        then(sprintRepository).should().delete(any(Sprint.class));
    }

    @Test
    @DisplayName("스프린트를 시작한다.")
    void startSprint() {
        // given
        SprintForm form = new SprintForm("Sprint 1", "Goal", LocalDate.now(), LocalDate.now().plusDays(7), false, true);
        given(projectMemberRepository.findByMemberIdAndProjectId(any(), any())).willReturn(Optional.of(projectMember));
        given(sprintRepository.findByProject_ProjectIdAndId(any(), any())).willReturn(Optional.of(sprint));
        given(sprintRepository.save(any(Sprint.class))).willReturn(sprint);

        // when
        SprintResponse response = sprintService.startSprint(memberId, projectId, sprintId, form);

        // then
        assertThat(response.isActivate()).isTrue();
        assertThat(response.endDate()).isEqualTo(form.getEndDate());
    }

    @Test
    @DisplayName("종료 날짜가 없을 경우 예외를 발생시킨다.")
    void startSprintThrowsExceptionWhenEndDateIsNull() {
        // given
        SprintForm form = new SprintForm("Sprint 1", "Goal", LocalDate.now(), null, false, true);
        given(projectMemberRepository.findByMemberIdAndProjectId(any(), any())).willReturn(Optional.of(projectMember));
        given(sprintRepository.findByProject_ProjectIdAndId(any(), any())).willReturn(Optional.of(sprint));

        // when & then
        assertThatThrownBy(() -> sprintService.startSprint(memberId, projectId, sprintId, form))
            .isInstanceOf(EndDateNullException.class);
    }

    @Test
    @DisplayName("제목이 없을 경우 업데이트에서 예외를 발생시킨다.")
    void updateSprintThrowsExceptionWhenTitleIsNull() {
        // given
        SprintForm form = new SprintForm(null, "Goal", LocalDate.now(), LocalDate.now().plusDays(7), false, false);
        given(projectMemberRepository.findByMemberIdAndProjectId(any(), any())).willReturn(Optional.of(projectMember));
        given(sprintRepository.findByProject_ProjectIdAndId(any(), any())).willReturn(Optional.of(sprint));

        // when & then
        assertThatThrownBy(() -> sprintService.updateSprint(memberId, projectId, sprintId, form))
            .isInstanceOf(TitleNullException.class);
    }

    @Test
    @DisplayName("제목이 없을 경우 스프린트 시작에서 예외를 발생시킨다.")
    void startSprintThrowsExceptionWhenTitleIsNull() {
        // given
        SprintForm form = new SprintForm(null, "Goal", LocalDate.now(), LocalDate.now().plusDays(7), false, true);
        given(projectMemberRepository.findByMemberIdAndProjectId(any(), any())).willReturn(Optional.of(projectMember));
        given(sprintRepository.findByProject_ProjectIdAndId(any(), any())).willReturn(Optional.of(sprint));

        // when & then
        assertThatThrownBy(() -> sprintService.startSprint(memberId, projectId, sprintId, form))
            .isInstanceOf(TitleNullException.class);
    }

    @Test
    @DisplayName("스프린트를 완료한다.")
    void completeSprint() {
        // given
        given(projectMemberRepository.findByMemberIdAndProjectId(any(), any())).willReturn(Optional.of(projectMember));
        given(sprintRepository.findByProject_ProjectIdAndId(any(), any())).willReturn(Optional.of(sprint));
        given(sprintRepository.save(any(Sprint.class))).willReturn(sprint);

        // when
        SprintResponse response = sprintService.completeSprint(memberId, projectId, sprintId);

        // then
        assertThat(response.isDone()).isTrue();
    }

    @Test
    @DisplayName("프로젝트가 없으면 예외를 발생시킨다.")
    void validateProjectThrowsExceptionWhenNotFound() {
        // given
        given(projectMemberRepository.findByMemberIdAndProjectId(any(), any())).willReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> sprintService.getAllSprints(memberId, projectId))
            .isInstanceOf(ProjectNotFoundException.class);
    }

    @Test
    @DisplayName("스프린트가 없으면 예외를 발생시킨다.")
    void validateSprintThrowsExceptionWhenNotFound() {
        // given
        given(projectMemberRepository.findByMemberIdAndProjectId(any(), any())).willReturn(Optional.of(projectMember));
        given(sprintRepository.findByProject_ProjectIdAndId(any(), any())).willReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> sprintService.getSprintById(memberId, projectId, sprintId))
            .isInstanceOf(SprintNotFoundException.class);
    }
}

