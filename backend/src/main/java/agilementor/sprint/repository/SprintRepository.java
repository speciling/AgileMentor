package agilementor.sprint.repository;

import agilementor.sprint.entity.Sprint;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long> {

    List<Sprint> findByProjectId(Long projectId);

    Optional<Sprint> findByProjectIdAndId(Long projectId, Long sprintId);
}
