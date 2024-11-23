package agilementor.project.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

import agilementor.member.entity.Member;
import agilementor.project.dto.response.InvitationGetResponse;
import agilementor.project.entity.Invitation;
import agilementor.project.entity.Project;
import agilementor.project.repository.InvitationRepository;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class InvitationServiceTest {

    @Mock
    private InvitationRepository invitationRepository;

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

        given(invitationRepository.findByInviteeId(inviteeId))
            .willReturn(List.of(invitation));

        // when
        List<InvitationGetResponse> invitationList = invitationService.getInvitationList(inviteeId);

        // then
        assertThat(invitationList.size()).isEqualTo(1);

        InvitationGetResponse invitationGetResponse = invitationList.getFirst();
        assertThat(invitationGetResponse.invitationId()).isEqualTo(invitationId);
        assertThat(invitationGetResponse.projectTitle()).isEqualTo(projectTitle);
        assertThat(invitationGetResponse.invitorName()).isEqualTo(invitorName);
    }
}