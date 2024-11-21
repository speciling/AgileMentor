package agilementor.project.service;

import agilementor.common.exception.ProjectNotFoundException;
import agilementor.member.dto.response.MemberGetResponse;
import agilementor.project.entity.ProjectMember;
import agilementor.project.repository.ProjectMemberRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProjectMemberService {

    private final ProjectMemberRepository projectMemberRepository;

    public ProjectMemberService(ProjectMemberRepository projectMemberRepository) {
        this.projectMemberRepository = projectMemberRepository;
    }

    public List<MemberGetResponse> getProjectMemberList(Long memberId, Long projectId) {

        List<ProjectMember> projectMemberList = projectMemberRepository.findByProjectId(projectId);

        boolean isNotMemberOfProject = projectMemberList.stream()
            .noneMatch(projectMember -> projectMember.getMember().getMemberId().equals(memberId));

        if (isNotMemberOfProject) {
            throw new ProjectNotFoundException();
        }

        return projectMemberList.stream()
            .map(projectMember -> MemberGetResponse.from(projectMember.getMember()))
            .toList();
    }

}
