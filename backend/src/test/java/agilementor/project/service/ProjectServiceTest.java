package agilementor.project.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

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
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private ProjectRespository projectRespository;

    @Mock
    private ProjectMemberRepository projectMemberRepository;

    @InjectMocks
    private ProjectService projectService;

    @Test
    @DisplayName("프로젝트를 생성할 수 있다.")
    void createProject() {
        // given
        Long memberId = 1L;
        ProjectCreateRequest projectCreateRequest = new ProjectCreateRequest("title");
        Project project = projectCreateRequest.toEntity();

        given(memberRepository.findById(any())).willReturn(Optional.of(getMember()));
        given(projectRespository.save(any())).willReturn(projectCreateRequest.toEntity());
        given(projectMemberRepository.save(any())).willReturn(null);

        // when
        ProjectResponse actual = projectService.createProject(memberId, projectCreateRequest);

        // then
        assertThat(actual).isEqualTo(ProjectResponse.from(project));
        then(projectMemberRepository).should().save(any());
    }


    @Test
    @DisplayName("참가한 프로젝트 목록을 조회할 수 있다.")
    void getProjectList() {
        // given
        Member member = new Member("email@email.com", "name", "pic.jpg");
        Project project1 = new Project("project1");
        Project project2 = new Project("project2");
        ProjectMember projectMember1 = new ProjectMember(project1, member);
        ProjectMember projectMember2 = new ProjectMember(project2, member);
        List<ProjectMember> projectMemberList = List.of(projectMember1, projectMember2);

        given(projectMemberRepository.findByMemberId(any())).willReturn(projectMemberList);

        // when
        List<ProjectResponse> projectList = projectService.getProjectList(1L);

        // then
        assertThat(projectList.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("특정 프로젝트 정보를 조회할 수 있다.")
    void getProject() {
        // given
        String projectTitle = "title";
        Member member = new Member("email@email.com", "name", "pic.jpg");
        Project project = new Project(projectTitle);
        ProjectMember projectMember = new ProjectMember(project, member);

        given(projectMemberRepository.findByMemberIdAndProjectId(any(), any()))
            .willReturn(Optional.of(projectMember));

        // when
        ProjectResponse actual = projectService.getProject(1L, 1L);

        // then
        assertThat(actual.title()).isEqualTo(projectTitle);
    }

    @Test
    @DisplayName("존재하지 않는 프로젝트 정보는 조회할 수 없다.")
    void getProjectFailIfNotExisting() {
        // given
        given(projectMemberRepository.findByMemberIdAndProjectId(any(), any()))
            .willReturn(Optional.empty());

        // when
        // then
        assertThatThrownBy(() -> projectService.getProject(1L, 1L))
            .isInstanceOf(ProjectNotFoundException.class);
    }

    @Test
    @DisplayName("참가하지 않은 프로젝트 정보는 조회할 수 없다.")
    void getProjectFailIfNotParticipating() {
        // given
        given(projectMemberRepository.findByMemberIdAndProjectId(any(), any()))
            .willReturn(Optional.empty());

        // when
        // then
        assertThatThrownBy(() -> projectService.getProject(1L, 1L))
            .isInstanceOf(ProjectNotFoundException.class);
    }

    @Test
    @DisplayName("프로젝트에 참가한 회원 목록을 조회할 수 있다.")
    void getProjectMemberList() {
        // given
        Long memberId = 1L;
        Member member1 = new Member("email1@email.com", "name", "pic.jpg");
        Member member2 = new Member("email2@email.com", "name", "pic.jpg");
        Member member3 = new Member("email3@email.com", "name", "pic.jpg");
        Project project = new Project("project");
        ProjectMember projectMember1 = new ProjectMember(project, member1);
        ProjectMember projectMember2 = new ProjectMember(project, member2);
        ProjectMember projectMember3 = new ProjectMember(project, member3);
        List<ProjectMember> projectMemberList = List.of(projectMember1, projectMember2,
            projectMember3);

        ReflectionTestUtils.setField(member1, "memberId", memberId);
        ReflectionTestUtils.setField(member2, "memberId", memberId + 1);
        ReflectionTestUtils.setField(member3, "memberId", memberId + 2);

        given(projectMemberRepository.findByProjectId(any())).willReturn(projectMemberList);

        // when
        List<MemberGetResponse> actual = projectService.getProjectMemberList(memberId, 1L);

        // then
        assertThat(actual.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("존재하지 않는 프로젝트의 회원 목록은 조회할 수 없다.")
    void getProjectMemberListFailIfNotExisting() {
        // given
        List<ProjectMember> projectMemberList = List.of();

        given(projectMemberRepository.findByProjectId(any())).willReturn(projectMemberList);

        // when
        // then
        assertThatThrownBy(() -> projectService.getProjectMemberList(1L, 1L))
            .isInstanceOf(ProjectNotFoundException.class);
    }

    @Test
    @DisplayName("참가하지 않은 프로젝트의 회원 목록은 조회할 수 없다.")
    void getProjectMemberListFailIfNotParticipating() {
        // given
        Long memberId = 1L;
        Long otherMemberId = 100L;
        Member member1 = new Member("email1@email.com", "name", "pic.jpg");
        Member member2 = new Member("email2@email.com", "name", "pic.jpg");
        Member member3 = new Member("email3@email.com", "name", "pic.jpg");
        Project project = new Project("project");
        ProjectMember projectMember1 = new ProjectMember(project, member1);
        ProjectMember projectMember2 = new ProjectMember(project, member2);
        ProjectMember projectMember3 = new ProjectMember(project, member3);
        List<ProjectMember> projectMemberList = List.of(projectMember1, projectMember2,
            projectMember3);

        ReflectionTestUtils.setField(member1, "memberId", memberId);
        ReflectionTestUtils.setField(member2, "memberId", memberId + 1);
        ReflectionTestUtils.setField(member3, "memberId", memberId + 2);

        given(projectMemberRepository.findByProjectId(any())).willReturn(projectMemberList);

        // when
        // then
        assertThatThrownBy(() -> projectService.getProjectMemberList(otherMemberId, 1L))
            .isInstanceOf(ProjectNotFoundException.class);
    }

    @Test
    @DisplayName("프로젝트 정보를 수정할 수 있다.")
    void updateProject() {
        // given
        String title = "title";
        String newTitle = "newTitle";
        Member member = new Member("email@email.com", "name", "pic.jpg");
        Project project = new Project(title);
        ProjectMember projectMember = new ProjectMember(project, member);
        ProjectUpdateRequest projectUpdateRequest = new ProjectUpdateRequest(newTitle);

        given(projectMemberRepository.findByMemberIdAndProjectId(any(), any()))
            .willReturn(Optional.of(projectMember));

        // when
        ProjectResponse actual = projectService.updateProject(1L, 1L, projectUpdateRequest);

        // then
        assertThat(actual.title()).isEqualTo(newTitle);
    }

    @Test
    @DisplayName("존재하지 않는 프로젝트 정보를 수정할 수 없다.")
    void updateProjectFailIfNotExisting() {
        // given
        given(projectMemberRepository.findByMemberIdAndProjectId(any(), any()))
            .willReturn(Optional.empty());
        ProjectUpdateRequest projectUpdateRequest = new ProjectUpdateRequest("newTitle");

        // when
        // then
        assertThatThrownBy(() -> projectService.updateProject(1L, 1L, projectUpdateRequest))
            .isInstanceOf(ProjectNotFoundException.class);
    }

    @Test
    @DisplayName("참가하지 않은 프로젝트 정보를 수정할 수 없다.")
    void updateProjectFailIfNotParticipating() {
        // given
        given(projectMemberRepository.findByMemberIdAndProjectId(any(), any()))
            .willReturn(Optional.empty());
        ProjectUpdateRequest projectUpdateRequest = new ProjectUpdateRequest("newTitle");

        // when
        // then
        assertThatThrownBy(() -> projectService.updateProject(1L, 1L, projectUpdateRequest))
            .isInstanceOf(ProjectNotFoundException.class);
    }

    @Test
    @DisplayName("관리자 권한이 없는 프로젝트 정보를 수정할 수 없다.")
    void updateProjectFailIfNotAdmin() {
        // todo: 권한 체크 기능 테스트 추가
    }

    @Test
    @DisplayName("프로젝트를 삭제할 수 있다")
    void deleteProject() {
        // given
        Member member = new Member("email@email.com", "name", "pic.jpg");
        Project project = new Project("title");
        ProjectMember projectMember = new ProjectMember(project, member);

        given(projectMemberRepository.findByMemberIdAndProjectId(any(), any()))
            .willReturn(Optional.of(projectMember));

        // when
        projectService.deleteProject(1L, 1L);

        // then
        then(projectMemberRepository).should().deleteAllByProject(project);
        then(projectRespository).should().delete(project);
    }

    @Test
    @DisplayName("존재하지 않는 프로젝트를 삭제할 수 없다")
    void deleteProjectFailIfNotExisting() {
        // given
        given(projectMemberRepository.findByMemberIdAndProjectId(any(), any()))
            .willReturn(Optional.empty());

        // when
        // then
        assertThatThrownBy(() -> projectService.deleteProject(1L, 1L))
            .isInstanceOf(ProjectNotFoundException.class);
    }

    @Test
    @DisplayName("참가하지 않은 프로젝트를 삭제할 수 없다")
    void deleteProjectFailIfNotParticipating() {
        // given
        given(projectMemberRepository.findByMemberIdAndProjectId(any(), any()))
            .willReturn(Optional.empty());

        // when
        // then
        assertThatThrownBy(() -> projectService.deleteProject(1L, 1L))
            .isInstanceOf(ProjectNotFoundException.class);
    }

    @Test
    @DisplayName("관리자 권한이 없는 프로젝트를 삭제할 수 없다.")
    void deleteProjectFailIfNotAdmin() {
        // todo: 권한 체크 기능 테스트 추가
    }

    private static Member getMember() {
        return new Member("email@email.com", "이름", "picture.jpg");
    }
}