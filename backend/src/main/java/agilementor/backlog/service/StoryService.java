package agilementor.backlog.service;

import agilementor.backlog.dto.request.StoryCreateRequest;
import agilementor.backlog.dto.response.StoryCreateResponse;
import agilementor.backlog.entity.Status;
import agilementor.backlog.entity.Story;
import agilementor.backlog.repository.StoryRepository;
import agilementor.common.exception.ProjectNotFoundException;
import agilementor.project.entity.Project;
import agilementor.project.entity.ProjectMember;
import agilementor.project.repository.ProjectMemberRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class StoryService {

    private final ProjectMemberRepository projectMemberRepository;
    private final StoryRepository storyRepository;

    public StoryService(ProjectMemberRepository projectMemberRepository, StoryRepository storyRepository) {
        this.projectMemberRepository = projectMemberRepository;
        this.storyRepository = storyRepository;
    }

    public StoryCreateResponse createStory(Long memberId, Long projectId,
        @Valid StoryCreateRequest storyCreateRequest) {

        Project project = getProject(memberId, projectId);
        Story story = storyRepository.save(storyCreateRequest.toEntity(project));

        return StoryCreateResponse.from(story);
    }

    private Project getProject(Long memberId, Long projectId) {
        ProjectMember projectMember = projectMemberRepository
            .findByMemberIdAndProjectId(memberId, projectId)
            .orElseThrow(ProjectNotFoundException::new);

        return projectMember.getProject();
    }
}
