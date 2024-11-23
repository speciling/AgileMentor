package agilementor.project.service;

import agilementor.project.dto.response.InvitationGetResponse;
import agilementor.project.entity.Invitation;
import agilementor.project.repository.InvitationRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class InvitationService {

    private final InvitationRepository invitationRepository;

    public InvitationService(InvitationRepository invitationRepository) {
        this.invitationRepository = invitationRepository;
    }

    public List<InvitationGetResponse> getInvitationList(Long memberId) {
        List<Invitation> invitationList = invitationRepository.findByInviteeId(memberId);

        return invitationList.stream()
            .map(InvitationGetResponse::from)
            .toList();
    }
}
