package agilementor.backlog.repository;

import agilementor.backlog.entity.Backlog;
import agilementor.project.entity.Project;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BacklogRepository extends JpaRepository<Backlog, Long> {

    List<Backlog> findByProject(Project project);
}
