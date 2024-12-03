package agilementor.backlog.repository;

import agilementor.backlog.entity.Backlog;
import agilementor.member.entity.Member;
import agilementor.project.entity.Project;
import agilementor.sprint.entity.Sprint;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BacklogRepository extends JpaRepository<Backlog, Long> {

    List<Backlog> findByProject(Project project);

    List<Backlog> findByProjectAndSprint(Project project, Sprint activeSprint);

    List<Backlog> findByAssigneeAndSprint(Member member, Sprint sprint);
}
