package agilementor.project.repository;

import agilementor.project.entity.ProjectMember;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long> {

    @Query("SELECT DISTINCT pm FROM ProjectMember pm JOIN FETCH pm.project WHERE pm.member.memberId = :memberId")
    List<ProjectMember> findByMemberId(@Param("memberId") Long memberId);
}
