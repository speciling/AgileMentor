package agilementor.project.service;

import agilementor.common.exception.InvalidInvitationException;
import agilementor.project.dto.response.InvitationGetResponse;
import agilementor.project.entity.Invitation;
import agilementor.project.entity.ProjectMember;
import agilementor.project.repository.InvitationRepository;
import agilementor.project.repository.ProjectMemberRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class InvitationService {

    private final InvitationRepository invitationRepository;
    private final ProjectMemberRepository projectMemberRepository;

    public InvitationService(InvitationRepository invitationRepository,
        ProjectMemberRepository projectMemberRepository) {
        this.invitationRepository = invitationRepository;
        this.projectMemberRepository = projectMemberRepository;
    }

    public List<InvitationGetResponse> getInvitationList(Long memberId) {
        List<Invitation> invitationList = invitationRepository.findByInviteeId(memberId);

        return invitationList.stream()
            .map(InvitationGetResponse::from)
            .toList();
    }

    public void acceptInvitation(Long memberId, Long invitationId) {
        Invitation invitation = invitationRepository.findById(invitationId)
            .orElseThrow(InvalidInvitationException::new);

        if (!invitation.getInvitee().getMemberId().equals(memberId)) {
            throw new InvalidInvitationException();
        }

        projectMemberRepository.save(
            new ProjectMember(invitation.getProject(), invitation.getInvitee(), false));

        invitationRepository.delete(invitation);
    }

    public void declineInvitation(Long memberId, Long invitationId) {
        Invitation invitation = invitationRepository.findById(invitationId)
            .orElseThrow(InvalidInvitationException::new);

        if (!invitation.getInvitee().getMemberId().equals(memberId)) {
            throw new InvalidInvitationException();
        }

        invitationRepository.delete(invitation);
    }
}
