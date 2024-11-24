package agilementor.project.dto.response;

import agilementor.project.entity.Invitation;

public record InvitationGetResponse(
    Long invitationId,
    String projectTitle,
    String invitorName
) {

    public static InvitationGetResponse from(Invitation invitation) {
        Long invitationId = invitation.getInvitationId();
        String projectTitle = invitation.getProject().getTitle();
        String invitorName = invitation.getInvitor().getName();

        return new InvitationGetResponse(invitationId, projectTitle, invitorName);
    }
}
