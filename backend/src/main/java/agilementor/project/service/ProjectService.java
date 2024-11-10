package agilementor.project.service;

import agilementor.common.exception.MemberNotFoundException;
import agilementor.member.entity.Member;
import agilementor.member.repository.MemberRepository;
import agilementor.project.dto.request.ProjectCreateRequest;
import agilementor.project.dto.response.ProjectResponse;
import agilementor.project.entity.Project;
import agilementor.project.entity.ProjectMember;
import agilementor.project.repository.ProjectMemberRepository;
import agilementor.project.repository.ProjectRespository;
import jakarta.transaction.Transactional;
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
        projectMemberRepository.save(new ProjectMember(project, member));

        return ProjectResponse.from(project);
    }
}
