package agilementor.member.repository;

import static org.assertj.core.api.Assertions.assertThat;

import agilementor.member.entity.Member;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원을 저장할 수 있다.")
    void save() {
        // given
        Member member = new Member("email@email.com", "name", "picture.jpg");

        // when
        member = memberRepository.save(member);

        // then
        assertThat(member.getMemberId()).isNotNull();
    }

    @Test
    @DisplayName("이메일로 회원을 찾을 수 있다.")
    void findByEmail() {
        // given
        String email = "email@email.com";
        Member member = memberRepository.save(new Member(email, "name", "picture.jpg"));

        // when
        Member foundMember = memberRepository.findByEmail(email)
            .orElseThrow(NoSuchElementException::new);

        // then
        assertThat(member).isEqualTo(foundMember);
    }

    @Test
    @DisplayName("찾으려는 email인 회원이 없으면 빈 Optional을 반환한다")
    void findByNotExistEmail() {
        // given
        String email = "email@email.com";
        String notExistEmail = "notExist@email.com";
        Member member = memberRepository.save(new Member(email, "name", "picture.jpg"));

        // when
        Optional<Member> foundMember = memberRepository.findByEmail(notExistEmail);

        // then
        assertThat(foundMember).isEmpty();
    }

}