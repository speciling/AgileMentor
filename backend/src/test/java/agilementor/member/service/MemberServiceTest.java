package agilementor.member.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import agilementor.member.dto.ParsedIdToken;
import agilementor.member.dto.response.MemberGetResponse;
import agilementor.member.entity.Member;
import agilementor.member.repository.MemberRepository;
import agilementor.member.util.JwtParser;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

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
        assertThat(member.getEmail()).isEqualTo(parsedIdToken.email());
        assertThat(member.getName()).isEqualTo(parsedIdToken.name());
        assertThat(member.getPicture()).isEqualTo(parsedIdToken.picture());
    }

    @Test
    @DisplayName("회원 정보를 확인할 수 있다.")
    void getMember() {
        // given
        Long memberId = 1L;
        String email = "testEmail@agilementor.kr";
        String name = "이름";
        String picture = "https://test.agilementor.kr/pic.jpg";

        Member member = new Member(email, name, picture);
        ReflectionTestUtils.setField(member, "memberId", memberId);

        given(memberRepository.findById(memberId)).willReturn(Optional.of(member));

        // when
        MemberGetResponse memberGetResponse = memberService.getMember(memberId);

        // then
        assertThat(memberGetResponse.memberId()).isEqualTo(memberId);
        assertThat(memberGetResponse.email()).isEqualTo(email);
        assertThat(memberGetResponse.name()).isEqualTo(name);
        assertThat(memberGetResponse.picture()).isEqualTo(picture);
    }

}