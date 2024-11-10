package agilementor.project.repository;

import agilementor.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRespository extends JpaRepository<Project, Long> {

}
