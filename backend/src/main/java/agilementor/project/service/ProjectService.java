package agilementor.project.service;

import agilementor.common.exception.MemberNotFoundException;
import agilementor.common.exception.NotProjectAdminException;
import agilementor.common.exception.ProjectNotFoundException;
import agilementor.member.dto.response.MemberGetResponse;
import agilementor.member.entity.Member;
import agilementor.member.repository.MemberRepository;
import agilementor.project.dto.request.ProjectCreateRequest;
import agilementor.project.dto.request.ProjectUpdateRequest;
import agilementor.project.dto.response.ProjectResponse;
import agilementor.project.entity.Project;
import agilementor.project.entity.ProjectMember;
import agilementor.project.repository.ProjectMemberRepository;
import agilementor.project.repository.ProjectRespository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProjectService {

    private final MemberRepository memberRepository;
    private final ProjectRespository projectRespository;
    private final ProjectMemberRepository projectMemberRepository;

    public ProjectService(MemberRepository memberRepository, ProjectRespository projectRespository,
        ProjectMemberRepository projectMemberRepository) {
        this.memberRepository = memberRepository;
        this.projectRespository = projectRespository;
        this.projectMemberRepository = projectMemberRepository;
    }

    public ProjectResponse createProject(Long memberId, ProjectCreateRequest projectCreateRequest) {

        Member member = memberRepository.findById(memberId)
            .orElseThrow(MemberNotFoundException::new);
        Project project = projectRespository.save(projectCreateRequest.toEntity());
        projectMemberRepository.save(new ProjectMember(project, member, true));

        return ProjectResponse.from(project);
    }

    public List<ProjectResponse> getProjectList(Long memberId) {

        List<ProjectMember> projectMemberList = projectMemberRepository.findByMemberId(memberId);
        return projectMemberList.stream()
            .map(projectMember -> ProjectResponse.from(projectMember.getProject()))
            .toList();
    }

    public ProjectResponse getProject(Long memberId, Long projectId) {

        ProjectMember projectMember = projectMemberRepository
            .findByMemberIdAndProjectId(memberId, projectId)
            .orElseThrow(ProjectNotFoundException::new);

        return ProjectResponse.from(projectMember.getProject());
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

    public ProjectResponse updateProject(Long memberId, Long projectId,
        ProjectUpdateRequest projectUpdateRequest) {

        ProjectMember projectMember = projectMemberRepository
            .findByMemberIdAndProjectId(memberId, projectId)
            .orElseThrow(ProjectNotFoundException::new);

        if (projectMember.isNotAdmin()) {
            throw new NotProjectAdminException();
        }

        Project project = projectMember.getProject();
        project.update(projectUpdateRequest.title());
        return ProjectResponse.from(project);
    }

    public void deleteProject(Long memberId, Long projectId) {

        ProjectMember projectMember = projectMemberRepository
            .findByMemberIdAndProjectId(memberId, projectId)
            .orElseThrow(ProjectNotFoundException::new);

        if (projectMember.isNotAdmin()) {
            throw new NotProjectAdminException();
        }

        Project project = projectMember.getProject();
        projectMemberRepository.deleteAllByProject(project);
        projectRespository.delete(project);
    }
}
