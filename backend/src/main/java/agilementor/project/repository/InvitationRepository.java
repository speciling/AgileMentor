package agilementor.project.repository;

import agilementor.member.entity.Member;
import agilementor.project.entity.Invitation;
import agilementor.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {

    boolean existsByMemberAndProject(Member member, Project project);
}
