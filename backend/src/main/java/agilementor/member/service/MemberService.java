package agilementor.member.service;

import agilementor.common.exception.MemberNotFoundException;
import agilementor.member.dto.ParsedIdToken;
import agilementor.member.dto.response.MemberGetResponse;
import agilementor.member.entity.Member;
import agilementor.member.repository.MemberRepository;
import agilementor.member.util.JwtParser;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MemberService {

    private final JwtParser jwtParser;
    private final MemberRepository memberRepository;

    public MemberService(JwtParser jwtParser, MemberRepository memberRepository) {
        this.jwtParser = jwtParser;
        this.memberRepository = memberRepository;
    }

    public Long registerOrUpdateMember(String idToken) {
        ParsedIdToken parsedIdToken = jwtParser.parseIdToken(idToken);

        Member member = memberRepository.findByEmail(parsedIdToken.email()).
            orElseGet(() -> memberRepository.save(parsedIdToken.toEntity()));

        member.update(parsedIdToken.toEntity());

        return member.getMemberId();
    }

    public MemberGetResponse getMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
            .orElseThrow(MemberNotFoundException::new);
        return MemberGetResponse.from(member);
    }
}
