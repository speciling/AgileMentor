package agilementor.project.repository;

import agilementor.member.entity.Member;
import agilementor.project.entity.Invitation;
import agilementor.project.entity.Project;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {

    boolean existsByProjectAndInvitee(Project project, Member invitee);

    @Query("SELECT DISTINCT i FROM Invitation i JOIN FETCH i.project JOIN FETCH i.invitor WHERE i.invitee.memberId = :memberId")
    List<Invitation> findByInviteeId(@Param("memberId") Long memberId);
}
