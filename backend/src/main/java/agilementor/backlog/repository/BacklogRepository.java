package agilementor.backlog.repository;

import agilementor.backlog.entity.Backlog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BacklogRepository extends JpaRepository<Backlog, Long> {

}
