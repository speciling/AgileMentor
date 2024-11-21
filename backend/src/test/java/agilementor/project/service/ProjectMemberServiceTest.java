package agilementor.project.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import agilementor.common.exception.KickOneselfException;
import agilementor.common.exception.MemberNotFoundException;
import agilementor.common.exception.NotProjectAdminException;
import agilementor.common.exception.ProjectNotFoundException;
import agilementor.member.entity.Member;
import agilementor.project.entity.Project;
import agilementor.project.entity.ProjectMember;
import agilementor.project.repository.ProjectMemberRepository;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class ProjectMemberServiceTest {

    @Mock
    private ProjectMemberRepository projectMemberRepository;

    @InjectMocks
    private ProjectMemberService projectMemberService;

    private static Long ADMIN_MEMBER_ID = 1L;
    private static Long MEMBER_ID_1 = 2L;
    private static Long MEMBER_ID_2 = 3L;
    private static Long OTHER_MEMBER_ID = 10L;
    private static Long PROJECT_ID = 1L;
    private static Long OTHER_PROJECT_ID = 2L;

    @Test
    @DisplayName("프로젝트에 참가한 회원 목록을 조회할 수 있다.")
    void getProjectMemberList() {
        // given
        List<ProjectMember> projectMemberList = createProjectMemberList();

        given(projectMemberRepository.findByProjectId(PROJECT_ID)).willReturn(projectMemberList);

        // when
        var actual = projectMemberService.getProjectMemberList(ADMIN_MEMBER_ID, PROJECT_ID);

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
        assertThatThrownBy(
            () -> projectMemberService.getProjectMemberList(MEMBER_ID_1, OTHER_PROJECT_ID))
            .isInstanceOf(ProjectNotFoundException.class);
    }

    @Test
    @DisplayName("참가하지 않은 프로젝트의 회원 목록은 조회할 수 없다.")
    void getProjectMemberListFailIfNotParticipating() {
        // given
        List<ProjectMember> projectMemberList = createProjectMemberList();

        given(projectMemberRepository.findByProjectId(PROJECT_ID)).willReturn(projectMemberList);

        // when
        // then
        assertThatThrownBy(
            () -> projectMemberService.getProjectMemberList(OTHER_MEMBER_ID, PROJECT_ID))
            .isInstanceOf(ProjectNotFoundException.class);
    }

    @Test
    @DisplayName("프로젝트 관리자는 프로젝트의 회원을 추방할 수 있다.")
    void kickMember() {
        // given
        List<ProjectMember> projectMemberList = createProjectMemberList();

        given(projectMemberRepository.findByProjectId(PROJECT_ID)).willReturn(projectMemberList);

        // when
        projectMemberService.kickMember(ADMIN_MEMBER_ID, PROJECT_ID, MEMBER_ID_1);

        // then
        then(projectMemberRepository).should().delete(any());
    }

    @Test
    @DisplayName("존재하지 않는 프로젝트의 회원을 추방할 수 없다.")
    void kickMemberFailIfNotExisting() {
        // given
        List<ProjectMember> projectMemberList = List.of();

        given(projectMemberRepository.findByProjectId(PROJECT_ID)).willReturn(projectMemberList);

        // when
        // then
        assertThatThrownBy(
            () -> projectMemberService.kickMember(MEMBER_ID_1, PROJECT_ID, MEMBER_ID_2))
            .isInstanceOf(ProjectNotFoundException.class);
    }

    @Test
    @DisplayName("프로젝트 관리자가 아니면 프로젝트의 회원을 추방할 수 없다.")
    void kickMemberFailIfNotAdmin() {
        // given
        List<ProjectMember> projectMemberList = createProjectMemberList();

        given(projectMemberRepository.findByProjectId(PROJECT_ID)).willReturn(projectMemberList);

        // when
        // then
        assertThatThrownBy(
            () -> projectMemberService.kickMember(MEMBER_ID_1, PROJECT_ID, MEMBER_ID_2))
            .isInstanceOf(NotProjectAdminException.class);
    }

    @Test
    @DisplayName("프로젝트 관리자가 스스로를 추방할 수 없다.")
    void kickOneSelfFail() {
        // given
        List<ProjectMember> projectMemberList = createProjectMemberList();

        given(projectMemberRepository.findByProjectId(PROJECT_ID)).willReturn(projectMemberList);

        // when
        // then
        assertThatThrownBy(
            () -> projectMemberService.kickMember(ADMIN_MEMBER_ID, PROJECT_ID, ADMIN_MEMBER_ID))
            .isInstanceOf(KickOneselfException.class);
    }

    @Test
    @DisplayName("프로젝트의 회원이 아닌 사람을 추방할 수 없다.")
    void kickMemberFailIfNotParticipating() {
        // given
        List<ProjectMember> projectMemberList = createProjectMemberList();

        given(projectMemberRepository.findByProjectId(PROJECT_ID)).willReturn(projectMemberList);

        // when
        // then
        assertThatThrownBy(
            () -> projectMemberService.kickMember(ADMIN_MEMBER_ID, PROJECT_ID, OTHER_MEMBER_ID))
            .isInstanceOf(MemberNotFoundException.class);
    }

    private List<ProjectMember> createProjectMemberList() {
        Member member1 = new Member("email1@email.com", "name", "pic.jpg");
        Member member2 = new Member("email2@email.com", "name", "pic.jpg");
        Member member3 = new Member("email3@email.com", "name", "pic.jpg");
        Project project = new Project("project");
        ProjectMember projectMember1 = new ProjectMember(project, member1, true);
        ProjectMember projectMember2 = new ProjectMember(project, member2, false);
        ProjectMember projectMember3 = new ProjectMember(project, member3, false);

        ReflectionTestUtils.setField(member1, "memberId", ADMIN_MEMBER_ID);
        ReflectionTestUtils.setField(member2, "memberId", MEMBER_ID_1);
        ReflectionTestUtils.setField(member3, "memberId", MEMBER_ID_2);
        ReflectionTestUtils.setField(project, "projectId", PROJECT_ID);

        return List.of(projectMember1, projectMember2, projectMember3);
    }
}