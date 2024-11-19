package agilementor.sprint.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import agilementor.sprint.dto.SprintForm;
import agilementor.sprint.dto.SprintResponse;
import agilementor.sprint.entity.Sprint;
import agilementor.sprint.repository.SprintRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class SprintServiceTest {

    @Mock
    private SprintRepository sprintRepository;

    @InjectMocks
    private SprintService sprintService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("스프린트를 생성한다")
    void createSprint() {
        // given
        long projectId = 1L;
        SprintForm sprintForm = new SprintForm("Sprint 1", "Goal 1", LocalDate.now(), LocalDate.now().plusDays(10), false, false);
        Sprint sprint = new Sprint(projectId, sprintForm.getTitle(), sprintForm.getGoal(), sprintForm.getStartDate(), sprintForm.getEndDate(), sprintForm.isDone(), sprintForm.isActivate());
        when(sprintRepository.save(any(Sprint.class))).thenReturn(sprint);

        // when
        SprintResponse result = sprintService.createSprint(projectId, sprintForm);

        // then
        assertNotNull(result);
        assertEquals(sprintForm.getTitle(), result.title());
        assertEquals(sprintForm.getGoal(), result.goal());
        verify(sprintRepository, times(1)).save(any(Sprint.class));
    }

    @Test
    @DisplayName("프로젝트 ID로 모든 스프린트를 조회한다")
    void getAllSprints() {
        // given
        long projectId = 1L;
        Sprint sprint = new Sprint(projectId, "Sprint 1", "Goal 1", LocalDate.now(), LocalDate.now().plusDays(10), false, false);
        when(sprintRepository.findByProjectId(projectId)).thenReturn(List.of(sprint));

        // when
        List<SprintResponse> result = sprintService.getAllSprints(projectId);

        // then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Sprint 1", result.get(0).title());
        verify(sprintRepository, times(1)).findByProjectId(projectId);
    }

    @Test
    @DisplayName("스프린트 ID로 스프린트를 조회한다")
    void getSprintById() {
        // given
        long projectId = 1L;
        long sprintId = 1L;
        Sprint sprint = new Sprint(projectId, "Sprint 1", "Goal 1", LocalDate.now(), LocalDate.now().plusDays(10), false, false);
        when(sprintRepository.findByProjectIdAndId(projectId, sprintId)).thenReturn(Optional.of(sprint));

        // when
        SprintResponse result = sprintService.getSprintById(projectId, sprintId);

        // then
        assertNotNull(result);
        assertEquals("Sprint 1", result.title());
        verify(sprintRepository, times(1)).findByProjectIdAndId(projectId, sprintId);
    }

    @Test
    @DisplayName("스프린트 정보를 업데이트한다")
    void updateSprint() {
        // given
        long projectId = 1L;
        long sprintId = 1L;
        SprintForm sprintForm = new SprintForm("Updated Sprint", "Updated Goal", LocalDate.now(), LocalDate.now().plusDays(10), false, false);
        Sprint sprint = new Sprint(projectId, "Sprint 1", "Goal 1", LocalDate.now(), LocalDate.now().plusDays(10), false, false);
        when(sprintRepository.findByProjectIdAndId(projectId, sprintId)).thenReturn(Optional.of(sprint));
        when(sprintRepository.save(any(Sprint.class))).thenReturn(sprint);

        // when
        SprintResponse result = sprintService.updateSprint(projectId, sprintId, sprintForm);

        // then
        assertNotNull(result);
        assertEquals(sprintForm.getTitle(), result.title());
        assertEquals(sprintForm.getGoal(), result.goal());
        verify(sprintRepository, times(1)).save(sprint);
    }

    @Test
    @DisplayName("스프린트를 삭제한다")
    void deleteSprint() {
        // given
        long projectId = 1L;
        long sprintId = 1L;
        Sprint sprint = new Sprint(projectId, "Sprint 1", "Goal 1", LocalDate.now(), LocalDate.now().plusDays(10), false, false);
        when(sprintRepository.findByProjectIdAndId(projectId, sprintId)).thenReturn(Optional.of(sprint));

        // when
        sprintService.deleteSprint(projectId, sprintId);

        // then
        verify(sprintRepository, times(1)).delete(sprint);
    }

    @Test
    @DisplayName("스프린트를 완료한다")
    void completeSprint() {
        // given
        long projectId = 1L;
        long sprintId = 1L;
        Sprint sprint = new Sprint(projectId, "Sprint 1", "Goal 1", LocalDate.now(), LocalDate.now().plusDays(10), false, false);
        when(sprintRepository.findByProjectIdAndId(projectId, sprintId)).thenReturn(Optional.of(sprint));
        sprint.complete();
        when(sprintRepository.save(sprint)).thenReturn(sprint);

        // when
        SprintResponse result = sprintService.completeSprint(projectId, sprintId);

        // then
        assertNotNull(result);
        assertTrue(result.isDone());
        verify(sprintRepository, times(1)).save(sprint);
    }

    @Test
    @DisplayName("스프린트를 시작하고 필드를 업데이트한다")
    void startSprint() {
        // given
        long projectId = 1L;
        long sprintId = 1L;
        SprintForm sprintForm = new SprintForm("Started Sprint", "New Goal", LocalDate.now(), LocalDate.now().plusDays(10), false, true);
        Sprint sprint = new Sprint(projectId, "Sprint 1", "Goal 1", LocalDate.now(), LocalDate.now().plusDays(10), false, false);

        when(sprintRepository.findByProjectIdAndId(projectId, sprintId)).thenReturn(Optional.of(sprint));
        when(sprintRepository.save(sprint)).thenReturn(sprint);

        // when
        SprintResponse result = sprintService.startSprint(projectId, sprintId, sprintForm);

        // then
        assertNotNull(result);
        assertTrue(result.isActivate()); // 활성화 상태 확인
        assertEquals(sprintForm.getTitle(), result.title());
        assertEquals(sprintForm.getGoal(), result.goal());
        assertEquals(sprintForm.getStartDate(), result.startDate());
        assertEquals(sprintForm.getEndDate(), result.endDate());
        verify(sprintRepository, times(1)).save(sprint);
    }
}
