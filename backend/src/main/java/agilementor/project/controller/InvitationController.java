package agilementor.project.controller;

import agilementor.project.dto.response.InvitationGetResponse;
import agilementor.project.service.InvitationService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RestController
@RequestMapping("/api/invitations")
public class InvitationController {

    private final InvitationService invitationService;

    public InvitationController(InvitationService invitationService) {
        this.invitationService = invitationService;
    }

    @GetMapping
    public List<InvitationGetResponse> getInvitationList(
        @SessionAttribute("memberId") Long memberId) {

        return invitationService.getInvitationList(memberId);
    }

}
