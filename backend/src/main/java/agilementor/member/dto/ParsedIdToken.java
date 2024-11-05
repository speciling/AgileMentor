package agilementor.member.dto;

import agilementor.member.entity.Member;

public record ParsedIdToken(
    String email,
    String name,
    String picture
) {

    public Member toEntity() {
        return new Member(email, name, picture);
    }
}
