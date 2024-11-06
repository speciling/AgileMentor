package agilementor.member.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import agilementor.member.dto.ParsedIdToken;
import agilementor.member.entity.Member;
import agilementor.member.repository.MemberRepository;
import agilementor.member.util.JwtParser;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private JwtParser jwtParser;

    @InjectMocks
    private MemberService memberService;

    @Test
    @DisplayName("새로운 회원을 등록할 수 있다.")
    void registerMember() {
        // given
        ParsedIdToken parsedIdToken = new ParsedIdToken("email@email.com", "name", "picture.jpg");
        given(memberRepository.findByEmail(any())).willReturn(Optional.empty());
        given(memberRepository.save(any())).willReturn(parsedIdToken.toEntity());
        given(jwtParser.parseIdToken(any())).willReturn(parsedIdToken);

        // when
        memberService.registerOrUpdateMember("token");

        // then
        then(memberRepository).should().save(any());
    }

    @Test
    @DisplayName("기존 회원 정보를 업데이트 할 수 있다.")
    void updateMember() {
        // given
        ParsedIdToken parsedIdToken = new ParsedIdToken("email@email.com", "name", "picture.jpg");
        Member member = new Member("", "", "");
        given(memberRepository.findByEmail(any())).willReturn(Optional.of(member));
        given(jwtParser.parseIdToken(any())).willReturn(parsedIdToken);

        // when
        memberService.registerOrUpdateMember("token");

        // then
        Assertions.assertThat(member.getEmail()).isEqualTo(parsedIdToken.email());
        Assertions.assertThat(member.getName()).isEqualTo(parsedIdToken.name());
        Assertions.assertThat(member.getPicture()).isEqualTo(parsedIdToken.picture());
    }

}