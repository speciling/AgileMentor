package agilementor.backlog.repository;

import agilementor.backlog.entity.Backlog;
import agilementor.backlog.entity.Story;
import agilementor.member.entity.Member;
import agilementor.project.entity.Project;
import agilementor.sprint.entity.Sprint;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BacklogRepository extends JpaRepository<Backlog, Long> {

    List<Backlog> findByProject(Project project);

    List<Backlog> findBySprint(Sprint activeSprint);

    List<Backlog> findByStory(Story story);

    List<Backlog> findByAssigneeAndSprint(Member member, Sprint sprint);

    Optional<Backlog> findByBacklogIdAndProject(Long backlogId, Project project);
}
