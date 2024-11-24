package agilementor.project.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import agilementor.common.exception.InvalidInvitationException;
import agilementor.member.entity.Member;
import agilementor.project.dto.response.InvitationGetResponse;
import agilementor.project.entity.Invitation;
import agilementor.project.entity.Project;
import agilementor.project.repository.InvitationRepository;
import agilementor.project.repository.ProjectMemberRepository;
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
class InvitationServiceTest {

    @Mock
    private InvitationRepository invitationRepository;

    @Mock
    private ProjectMemberRepository projectMemberRepository;

    @InjectMocks
    private InvitationService invitationService;

    @Test
    @DisplayName("초대 목록을 조회할 수 있다.")
    void getInvitationList() {
        // given
        Long invitationId = 1L;
        Long inviteeId = 1L;
        String projectTitle = "프로젝트";
        String invitorName = "초대자";

        Project project = new Project(projectTitle);
        Member invitor = new Member("invitor@email.com", invitorName, "pic.jpg");
        Member invitee = new Member("invitee@email.com", "피초대자", "pic.jpg");
        Invitation invitation = new Invitation(project, invitee, invitor);
        ReflectionTestUtils.setField(invitation, "invitationId", invitationId);

        given(invitationRepository.findByInviteeId(inviteeId)).willReturn(List.of(invitation));

        // when
        List<InvitationGetResponse> invitationList = invitationService.getInvitationList(inviteeId);

        // then
        assertThat(invitationList.size()).isEqualTo(1);

        InvitationGetResponse invitationGetResponse = invitationList.getFirst();
        assertThat(invitationGetResponse.invitationId()).isEqualTo(invitationId);
        assertThat(invitationGetResponse.projectTitle()).isEqualTo(projectTitle);
        assertThat(invitationGetResponse.invitorName()).isEqualTo(invitorName);
    }

    @Test
    @DisplayName("초대를 수락할 수 있다.")
    void acceptInvitation() {
        // given
        Long invitationId = 1L;
        Long inviteeId = 1L;

        Project project = new Project("title");
        Member invitor = new Member("invitor@email.com", "invitor", "pic.jpg");
        Member invitee = new Member("invitee@email.com", "피초대자", "pic.jpg");
        ReflectionTestUtils.setField(invitee, "memberId", inviteeId);
        Invitation invitation = new Invitation(project, invitee, invitor);

        given(invitationRepository.findById(invitationId)).willReturn(Optional.of(invitation));

        // when
        invitationService.acceptInvitation(inviteeId, invitationId);

        // then
        then(projectMemberRepository).should().save(any());
        then(invitationRepository).should().delete(any());
    }

    @Test
    @DisplayName("존재하지 않는 id의 초대를 수락할 수 없다.")
    void acceptInvitationFailIfNotExisting() {
        // given
        Long invitationId = 1L;
        Long inviteeId = 1L;

        given(invitationRepository.findById(invitationId)).willReturn(Optional.empty());

        // when
        // then
        assertThatThrownBy(() -> invitationService.acceptInvitation(inviteeId, invitationId))
            .isInstanceOf(InvalidInvitationException.class);
    }

    @Test
    @DisplayName("자신이 초대 대상이 아닌 초대를 수락할 수 없다.")
    void acceptInvitationFailIfNotInvitee() {
        // given
        Long invitationId = 1L;
        Long inviteeId = 1L;
        Long loginMemberId = 2L;

        Project project = new Project("title");
        Member invitor = new Member("invitor@email.com", "invitor", "pic.jpg");
        Member invitee = new Member("invitee@email.com", "피초대자", "pic.jpg");
        ReflectionTestUtils.setField(invitee, "memberId", inviteeId);
        Invitation invitation = new Invitation(project, invitee, invitor);

        given(invitationRepository.findById(invitationId)).willReturn(Optional.of(invitation));

        // when
        // then
        assertThatThrownBy(() -> invitationService.acceptInvitation(loginMemberId, invitationId))
            .isInstanceOf(InvalidInvitationException.class);
    }

    @Test
    @DisplayName("초대를 거절할 수 있다.")
    void declineInvitation() {
        // given
        Long invitationId = 1L;
        Long inviteeId = 1L;

        Project project = new Project("title");
        Member invitor = new Member("invitor@email.com", "invitor", "pic.jpg");
        Member invitee = new Member("invitee@email.com", "피초대자", "pic.jpg");
        ReflectionTestUtils.setField(invitee, "memberId", inviteeId);
        Invitation invitation = new Invitation(project, invitee, invitor);

        given(invitationRepository.findById(invitationId)).willReturn(Optional.of(invitation));

        // when
        invitationService.declineInvitation(inviteeId, invitationId);

        // then
        then(invitationRepository).should().delete(any());
    }

    @Test
    @DisplayName("존재하지 않는 id의 초대를 거절할 수 없다.")
    void declineInvitationFailIfNotExisting() {
        // given
        Long invitationId = 1L;
        Long inviteeId = 1L;

        given(invitationRepository.findById(invitationId)).willReturn(Optional.empty());

        // when
        // then
        assertThatThrownBy(() -> invitationService.declineInvitation(inviteeId, invitationId))
            .isInstanceOf(InvalidInvitationException.class);
    }

    @Test
    @DisplayName("자신이 초대 대상이 아닌 초대를 거절할 수 없다.")
    void declineInvitationFailIfNotInvitee() {
        // given
        Long invitationId = 1L;
        Long inviteeId = 1L;
        Long loginMemberId = 2L;

        Project project = new Project("title");
        Member invitor = new Member("invitor@email.com", "invitor", "pic.jpg");
        Member invitee = new Member("invitee@email.com", "피초대자", "pic.jpg");
        ReflectionTestUtils.setField(invitee, "memberId", inviteeId);
        Invitation invitation = new Invitation(project, invitee, invitor);

        given(invitationRepository.findById(invitationId)).willReturn(Optional.of(invitation));

        // when
        // then
        assertThatThrownBy(() -> invitationService.declineInvitation(loginMemberId, invitationId))
            .isInstanceOf(InvalidInvitationException.class);
    }
}