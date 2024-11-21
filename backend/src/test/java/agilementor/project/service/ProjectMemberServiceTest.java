package agilementor.project.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import agilementor.common.exception.ProjectNotFoundException;
import agilementor.member.dto.response.MemberGetResponse;
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

    @Test
    @DisplayName("프로젝트에 참가한 회원 목록을 조회할 수 있다.")
    void getProjectMemberList() {
        // given
        Long memberId = 1L;
        Member member1 = new Member("email1@email.com", "name", "pic.jpg");
        Member member2 = new Member("email2@email.com", "name", "pic.jpg");
        Member member3 = new Member("email3@email.com", "name", "pic.jpg");
        Project project = new Project("project");
        ProjectMember projectMember1 = new ProjectMember(project, member1, true);
        ProjectMember projectMember2 = new ProjectMember(project, member2, false);
        ProjectMember projectMember3 = new ProjectMember(project, member3, false);
        List<ProjectMember> projectMemberList = List.of(projectMember1, projectMember2,
            projectMember3);

        ReflectionTestUtils.setField(member1, "memberId", memberId);
        ReflectionTestUtils.setField(member2, "memberId", memberId + 1);
        ReflectionTestUtils.setField(member3, "memberId", memberId + 2);

        given(projectMemberRepository.findByProjectId(any())).willReturn(projectMemberList);

        // when
        List<MemberGetResponse> actual = projectMemberService.getProjectMemberList(memberId, 1L);

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
        assertThatThrownBy(() -> projectMemberService.getProjectMemberList(1L, 1L))
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
        ProjectMember projectMember1 = new ProjectMember(project, member1, true);
        ProjectMember projectMember2 = new ProjectMember(project, member2, false);
        ProjectMember projectMember3 = new ProjectMember(project, member3, false);
        List<ProjectMember> projectMemberList = List.of(projectMember1, projectMember2,
            projectMember3);

        ReflectionTestUtils.setField(member1, "memberId", memberId);
        ReflectionTestUtils.setField(member2, "memberId", memberId + 1);
        ReflectionTestUtils.setField(member3, "memberId", memberId + 2);

        given(projectMemberRepository.findByProjectId(any())).willReturn(projectMemberList);

        // when
        // then
        assertThatThrownBy(() -> projectMemberService.getProjectMemberList(otherMemberId, 1L))
            .isInstanceOf(ProjectNotFoundException.class);
    }
}