package agilementor.project.repository;

import agilementor.project.entity.Project;
import agilementor.project.entity.ProjectMember;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long> {

    @Query("SELECT DISTINCT pm FROM ProjectMember pm JOIN FETCH pm.project WHERE pm.member.memberId = :memberId")
    List<ProjectMember> findByMemberId(@Param("memberId") Long memberId);

    @Query("SELECT DISTINCT pm FROM ProjectMember pm JOIN FETCH pm.member WHERE pm.project.projectId = :projectId")
    List<ProjectMember> findByProjectId(@Param("projectId") Long projectId);

    @Query("SELECT DISTINCT pm FROM ProjectMember pm JOIN FETCH pm.project WHERE pm.member.memberId = :memberId AND pm.project.projectId = :projectId")
    Optional<ProjectMember> findByMemberIdAndProjectId(@Param("memberId") Long memberId,
        @Param("projectId") Long projectId);

    void deleteAllByProject(Project project);
}
