package agilementor.backlog.service;

import agilementor.backlog.dto.request.BacklogCreateRequest;
import agilementor.backlog.dto.response.BacklogCreateResponse;
import agilementor.backlog.entity.Backlog;
import agilementor.backlog.entity.Story;
import agilementor.backlog.repository.BacklogRepository;
import agilementor.backlog.repository.StoryRepository;
import agilementor.common.exception.MemberNotFoundException;
import agilementor.common.exception.ProjectNotFoundException;
import agilementor.common.exception.SprintNotFoundException;
import agilementor.common.exception.StoryNotFoundException;
import agilementor.member.entity.Member;
import agilementor.member.repository.MemberRepository;
import agilementor.project.entity.ProjectMember;
import agilementor.project.repository.ProjectMemberRepository;
import agilementor.sprint.entity.Sprint;
import agilementor.sprint.repository.SprintRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BacklogService {

    private final MemberRepository memberRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final SprintRepository sprintRepository;
    private final BacklogRepository backlogRepository;
    private final StoryRepository storyRepository;

    public BacklogService(MemberRepository memberRepository,
        ProjectMemberRepository projectMemberRepository,
        SprintRepository sprintRepository, BacklogRepository backlogRepository,
        StoryRepository storyRepository) {
        this.memberRepository = memberRepository;
        this.projectMemberRepository = projectMemberRepository;
        this.sprintRepository = sprintRepository;
        this.backlogRepository = backlogRepository;
        this.storyRepository = storyRepository;
    }

    public BacklogCreateResponse createBacklog(Long memberId, Long projectId,
        BacklogCreateRequest backlogCreateRequest) {

        Long sprintId = backlogCreateRequest.sprintId();
        Long storyId = backlogCreateRequest.storyId();
        Long assigneeId = backlogCreateRequest.memberId();
        Backlog backlog = backlogCreateRequest.toEntity();

        ProjectMember projectMember = projectMemberRepository
            .findByMemberIdAndProjectId(memberId, projectId)
            .orElseThrow(ProjectNotFoundException::new);

        backlog.setProject(projectMember.getProject());

        if (sprintId != null) {
            Sprint sprint = sprintRepository.findById(sprintId)
                .orElseThrow(SprintNotFoundException::new);
            backlog.setSprint(sprint);
        }

        if (storyId != null) {
            Story story = storyRepository.findById(storyId)
                .orElseThrow(StoryNotFoundException::new);
            backlog.setStory(story);
        }

        if (assigneeId != null) {
            Member assignee = memberRepository.findById(assigneeId)
                .orElseThrow(MemberNotFoundException::new);
            backlog.setAssignee(assignee);
        }

        Backlog savedBacklog = backlogRepository.save(backlog);

        return BacklogCreateResponse.from(savedBacklog);
    }
}
