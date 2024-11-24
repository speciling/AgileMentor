package agilementor.project.dto.response;

import agilementor.member.entity.Member;
import agilementor.project.entity.ProjectMember;

public record ProejctMemberResponse(
    Long memberId,
    String name,
    String profileImageUrl,
    boolean isAdmin
) {

    public static ProejctMemberResponse from(ProjectMember projectMember) {
        Member member = projectMember.getMember();
        return new ProejctMemberResponse(member.getMemberId(), member.getName(),
            member.getPicture(), projectMember.isAdmin());
    }
}
