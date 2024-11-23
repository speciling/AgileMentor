package agilementor.member.dto.response;

import agilementor.member.entity.Member;

public record MemberGetResponse(
    Long memberId,
    String email,
    String name,
    String picture
) {

    public static MemberGetResponse from(Member member) {
        return new MemberGetResponse(member.getMemberId(), member.getEmail(), member.getName(),
            member.getPicture());
    }
}
