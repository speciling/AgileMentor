package agilementor.backlog.service;

import agilementor.backlog.dto.request.StoryCreateRequest;
import agilementor.backlog.dto.request.StoryUpdateRequest;
import agilementor.backlog.dto.response.StoryCreateResponse;
import agilementor.backlog.dto.response.StoryGetResponse;
import agilementor.backlog.dto.response.StoryUpdateResponse;
import agilementor.backlog.entity.Backlog;
import agilementor.backlog.entity.Status;
import agilementor.backlog.entity.Story;
import agilementor.backlog.repository.BacklogRepository;
import agilementor.backlog.repository.StoryRepository;
import agilementor.common.exception.ProjectNotFoundException;
import agilementor.common.exception.StoryNotFoundException;
import agilementor.project.entity.Project;
import agilementor.project.entity.ProjectMember;
import agilementor.project.repository.ProjectMemberRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class StoryService {

    private final ProjectMemberRepository projectMemberRepository;
    private final StoryRepository storyRepository;
    private final BacklogRepository backlogRepository;

    public StoryService(ProjectMemberRepository projectMemberRepository,
        StoryRepository storyRepository,
        BacklogRepository backlogRepository) {
        this.projectMemberRepository = projectMemberRepository;
        this.storyRepository = storyRepository;
        this.backlogRepository = backlogRepository;
    }

    public StoryCreateResponse createStory(Long memberId, Long projectId,
        StoryCreateRequest storyCreateRequest) {

        Project project = getProject(memberId, projectId);
        Story story = storyRepository.save(storyCreateRequest.toEntity(project));

        return StoryCreateResponse.from(story);
    }

    public List<StoryGetResponse> getStoryList(Long memberId, Long projectId) {

        Project project = getProject(memberId, projectId);
        List<Story> storyList = storyRepository.findByProject(project);

        return storyList.stream()
            .map(story -> StoryGetResponse.from(story, calculateStoryStatus(story)))
            .toList();
    }

    public StoryGetResponse getStory(Long memberId, Long projectId, Long storyId) {
        Project project = getProject(memberId, projectId);

        Story story = storyRepository.findByStoryIdAndProject(storyId, project)
            .orElseThrow(StoryNotFoundException::new);

        return StoryGetResponse.from(story, calculateStoryStatus(story));
    }

    public StoryUpdateResponse updateStory(Long memberId, Long projectId, Long storyId,
        StoryUpdateRequest storyUpdateRequest) {
        Project project = getProject(memberId, projectId);

        Story story = storyRepository.findByStoryIdAndProject(storyId, project)
            .orElseThrow(StoryNotFoundException::new);

        story.update(storyUpdateRequest.title(), storyUpdateRequest.description());

        return StoryUpdateResponse.from(story, calculateStoryStatus(story));
    }

    public void deleteStory(Long memberId, Long projectId, Long storyId) {
        Project project = getProject(memberId, projectId);

        Story story = storyRepository.findByStoryIdAndProject(storyId, project)
            .orElseThrow(StoryNotFoundException::new);

        List<Backlog> backlogList = backlogRepository.findByStory(story);

        for (Backlog backlog : backlogList) {
            backlog.setStory(null);
        }

        storyRepository.delete(story);
    }

    private Project getProject(Long memberId, Long projectId) {
        ProjectMember projectMember = projectMemberRepository
            .findByMemberIdAndProjectId(memberId, projectId)
            .orElseThrow(ProjectNotFoundException::new);

        return projectMember.getProject();
    }

    private Status calculateStoryStatus(Story story) {
        List<Backlog> backlogList = backlogRepository.findByStory(story);

        boolean isDone = backlogList.stream()
            .allMatch(Backlog::isDone);

        if (!backlogList.isEmpty() && isDone) {
            return Status.DONE;
        }

        return Status.IN_PROGRESS;
    }

}
